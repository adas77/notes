package pl.backend.Service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pl.backend.Auth.AuthRequest;
import pl.backend.Auth.AuthResponse;
import pl.backend.Auth.RegisterRequest;
import pl.backend.Config.JwtService;
import pl.backend.Model.EUserRole;
import pl.backend.Model.User;
import pl.backend.Repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final UserRepository repository;
        private final UserService userService;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {
                var user = User.builder()
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .dateOfSignUp(LocalDateTime.now())
                                .accountNonLocked(true)
                                .failedAttempt(0)
                                .enabled(true)
                                .role(EUserRole.ROLE_USER)
                                .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse authenticate(AuthRequest request) {
                User user = repository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

                if (!user.getAccountNonLocked()) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }

                try {
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        request.getUsername(),
                                                        request.getPassword()));

                        var jwtToken = jwtService.generateToken(user);
                        AuthResponse authResponse = AuthResponse.builder()
                                        .token(jwtToken)
                                        .build();

                        if (user.getFailedAttempt() > 0) {
                                userService.resetFailedAttempts(user.getUsername());
                        }
                        return authResponse;
                } catch (Exception e) {
                        if (user.isEnabled() && user.isAccountNonLocked()) {
                                if (user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                                        userService.increaseFailedAttempts(user);
                                } else {
                                        userService.lock(user);
                                }
                        } else if (!user.isAccountNonLocked()) {
                                userService.unlockWhenTimeExpired(user);
                        }
                        repository.save(user);
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);

                }
        }

}

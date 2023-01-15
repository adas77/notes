package pl.backend.Service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.backend.Auth.AuthRequest;
import pl.backend.Auth.AuthResponse;
import pl.backend.Auth.RegisterRequest;
import pl.backend.Config.JwtService;
import pl.backend.Model.EUserRole;
import pl.backend.Model.User;
import pl.backend.Repository.UserRepository;
import pl.backend.utils.PasswordStrength;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
        private final UserRepository repository;
        private final UserService userService;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {

                // TODO: FIXME !!!!
                log.info(request.getPassword());
                // if (!PasswordStrength.validateCommonPass(request.getPassword())
                // || !PasswordStrength.validatePassStrength(request.getPassword())) {
                // throw new IllegalArgumentException();
                // }

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
                log.info("jestem00");

                // authenticationManager.authenticate(
                // new UsernamePasswordAuthenticationToken(
                // request.getUsername(),
                // request.getPassword()));
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
                        log.info("token" + jwtToken);

                        AuthResponse authResponse = AuthResponse.builder()
                                        .token(jwtToken)
                                        .build();

                        if (user.getFailedAttempt() > 0) {
                                userService.resetFailedAttempts(user.getUsername());
                        }
                        return authResponse;
                } catch (Exception e) {
                        // String exception = "";
                        if (user.isEnabled() && user.isAccountNonLocked()) {
                                if (user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                                        userService.increaseFailedAttempts(user);
                                } else {
                                        userService.lock(user);
                                        // exception = "Your account has been locked due to "
                                        // + UserService.MAX_FAILED_ATTEMPTS + " failed attempts.";
                                }
                        } else if (!user.isAccountNonLocked()) {
                                if (userService.unlockWhenTimeExpired(user)) {
                                        // exception = "Your account has been unlocked. Please try to login again.";
                                }
                        }
                        repository.save(user);
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);

                }
        }

}

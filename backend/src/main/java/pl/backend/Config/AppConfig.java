package pl.backend.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.backend.Auth.RegisterRequest;
import pl.backend.Model.Note;
import pl.backend.Model.NoteStatus;
import pl.backend.Repository.UserRepository;
import pl.backend.Service.AuthService;
import pl.backend.Service.NoteService;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;
    private final NoteService noteService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Transactional
    public CommandLineRunner loadData(AuthService authService) {
        return (args) -> {
            String u1 = "qwertyuiop123456789";
            String e1 = "qwertyuiop@gmail.com";
            String p1 = "!as#(Hseh5ejddBBvdSFseg%%g";
            String u2 = "_" + u1;
            String e2 = "_" + e1;
            String p2 = "_" + p1;

            authService.register(new RegisterRequest(u1, e1, p1));
            authService.register(new RegisterRequest(u2, e2, p2));

            Note n1pub = new Note("PUBLIC FROM U1", NoteStatus.PUBLIC);
            Note n2pub = new Note("PUBLIC FROM U2", NoteStatus.PUBLIC);

            Note n1priv = new Note("PRIV FROM U1", NoteStatus.PRIVATE);
            Note n2priv = new Note("PRIV FROM U2", NoteStatus.PRIVATE);

            Note n1encoded = new Note("ENCODED FROM U1", NoteStatus.PRIVATE_ENCODED);
            Note n2encoded = new Note("ENCODED FROM U2", NoteStatus.PRIVATE_ENCODED);

            noteService.create(u1, n1pub, "null");
            noteService.create(u2, n2pub, "null");

            noteService.create(u1, n1priv, "null");
            noteService.create(u2, n2priv, "null");

            noteService.create(u1, n1encoded, p1);
            noteService.create(u2, n2encoded, p2);

        };
    }

}
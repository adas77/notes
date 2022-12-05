package pl.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import pl.backend.Model.EUserRole;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager get() {
        // System.out.println("user:" + EUserRole.ROLE_USER.name());
        // System.out.println("admin:" + EUserRole.ROLE_ADMIN.name());

        UserDetails user = User.withUsername("basic")
                .password(getBcryptPasswordEncoder().encode("basic123"))
                .authorities(EUserRole.ROLE_USER.name())
                // .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(getBcryptPasswordEncoder().encode("admin123"))
                .authorities(EUserRole.ROLE_ADMIN.name())
                // .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((autz) -> autz
                        .antMatchers("/for-user")
                        .hasAnyAuthority(EUserRole.ROLE_ADMIN.name(), EUserRole.ROLE_USER.name())
                        .antMatchers("/for-admin").hasAuthority(EUserRole.ROLE_ADMIN.name()))
                .formLogin((formLogin) -> formLogin.permitAll())
                .logout()
                .logoutSuccessUrl("/bye").permitAll();
        return http.build();
    }
}
package pl.backend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import pl.backend.Model.EUserRole;
import pl.backend.Service.UserDetailsServiceImpl;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public InMemoryUserDetailsManager get() {
    // // System.out.println("user:" + EUserRole.ROLE_USER.name());
    // // System.out.println("admin:" + EUserRole.ROLE_ADMIN.name());

    // UserDetails user = User.withUsername("basic")
    // .password(passwordEncoder().encode("basic123"))
    // .authorities(EUserRole.ROLE_USER.name())
    // // .roles("USER")
    // .build();
    // UserDetails admin = User.withUsername("admin")
    // .password(passwordEncoder().encode("admin123"))
    // .authorities(EUserRole.ROLE_ADMIN.name())
    // // .roles("ADMIN")
    // .build();
    // return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    // }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // @Bean
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.userDetailsService(userDetailsService);
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http // TODO: CSRF
        // http.csrf().disable()
        http.cors().and().csrf().disable()
                .authorizeRequests((autz) -> autz
                        .antMatchers("/hello")
                        .authenticated()
                        .antMatchers("/for-user")
                        .hasAnyAuthority(EUserRole.ROLE_ADMIN.name(), EUserRole.ROLE_USER.name())
                        .antMatchers("/for-admin").hasAuthority(EUserRole.ROLE_ADMIN.name())
                        .antMatchers("/note/*").permitAll()
                        .antMatchers("/user/*").permitAll()
                        // .anyRequest().denyAll()
                        )
                .formLogin((formLogin) -> formLogin.permitAll())
                .logout()
                .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
package xyz.mikavee.CRUDDEmo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import xyz.mikavee.CRUDDEmo.services.TrainerService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final TrainerService trainerService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())// Disable CSRF (Cross-Site Request Forgery) protection
                .authorizeHttpRequests(req -> {  // Configure authorization rules for HTTP requests
                    req.requestMatchers(HttpMethod.GET, "/api/pokemons").permitAll();
                    req.requestMatchers( "/api/pokemons").authenticated();
                    req.requestMatchers("/login").permitAll();

                });



        return http.build();

    }
}

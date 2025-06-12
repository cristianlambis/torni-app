package com.ralphdoe.torni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Definimos qué rutas quedan públicas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // Swagger / Springfox
                                "/v2/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                // Actuator health
                                "/actuator/health"
                        ).permitAll()
                        // El resto exige autenticación
                        .anyRequest().authenticated()
                )
                // Habilita OAuth2 Login (Google, Facebook, etc.)
                .oauth2Login(Customizer.withDefaults())
                // Para el MVP desactivamos CSRF (ajusta en producción según necesidad)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

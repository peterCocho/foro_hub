package com.aluracursos.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración temporal de seguridad:
 * Permite acceso libre a todos los endpoints.
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())           // Desactiva protección CSRF (recomendado para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())           // Permite acceso a todos los endpoints
                .build();
    }
}

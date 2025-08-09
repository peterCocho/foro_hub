package com.aluracursos.backend.service;

import com.aluracursos.backend.domain.user.AuthResponse;
import com.aluracursos.backend.dto.LoginForm;
import com.aluracursos.backend.infra.security.TokenService;
import com.aluracursos.backend.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public AuthResponse authenticate(LoginForm loginForm) {
        logger.info("Attempting authentication for user: {}", loginForm.email());

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginForm.email(),
                loginForm.password()
        );

        logger.info("Authenticating with AuthenticationManager...");
        Authentication authenticated = authenticationManager.authenticate(authToken);
        User user = (User) authenticated.getPrincipal();

        logger.info("User authenticated successfully: {}", user.getUsername());
        String token = tokenService.generateToken(user.getUsername());
        logger.info("Token generated successfully.");

        return new AuthResponse(user.getUsername(), token);
    }
}
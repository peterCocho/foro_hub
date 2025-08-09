package com.aluracursos.backend.controller;

import com.aluracursos.backend.domain.user.AuthResponse;
import com.aluracursos.backend.dto.LoginForm;
import com.aluracursos.backend.service.AuthService;
import com.aluracursos.backend.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    // private final TokenService tokenService;

    public AuthController(AuthService authService) {
        this.authService = authService;
        // this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginForm loginForm) {
        var response = authService.authenticate(loginForm);
        return ResponseEntity.ok(response);
    }


}

package com.aluracursos.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank(message = "Email is required.") String email,
        @NotBlank(message = "Password is required.") String password
) {}

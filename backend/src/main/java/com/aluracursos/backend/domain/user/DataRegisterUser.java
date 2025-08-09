package com.aluracursos.backend.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Using a Java 'record' for a concise and immutable DTO.
public record DataRegisterUser(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
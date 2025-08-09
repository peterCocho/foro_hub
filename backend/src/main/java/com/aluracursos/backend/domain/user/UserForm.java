package com.aluracursos.backend.domain.user;

import jakarta.validation.constraints.*;

public record UserForm(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Email is required.")
        @Email(message = "Must be a valid email address.")
        String email,

        @NotBlank(message = "Password cannot be blank.")
        @Size(min = 6, message = "Password must be at least 6 characters long.")
        String password

) {}

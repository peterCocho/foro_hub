package com.aluracursos.backend.domain.user;

// Este DTO se usa para exponer de forma segura los datos del usuario a través de la API.
public record UserDTO(
        Long id,
        String name,
        String email
) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
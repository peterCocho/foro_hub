package com.aluracursos.backend.domain.user;

/**
 * A simple DTO (Data Transfer Object) to carry the JWT token and username
 * back to the client after a successful authentication.
 */
public record AuthResponse(String username, String token) {}

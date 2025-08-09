package com.aluracursos.backend.domain.response;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long id,
        String message,
        String authorName,
        Long topicId,
        LocalDateTime creationDate,
        Boolean isSolution
) {
    public ResponseDTO(Response response) {
        this(
                response.getId(),
                response.getMessage(),
                response.getAuthor().getName(),
                response.getTopic().getId(),
                response.getCreationDate(),
                response.isSolution()
        );
    }
}

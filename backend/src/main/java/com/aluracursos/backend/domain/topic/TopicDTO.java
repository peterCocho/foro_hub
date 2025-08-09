package com.aluracursos.backend.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicDTO(
        @NotBlank(message = "Title cannot be blank.")
        @Size(min = 5, max = 100, message = "the title must be between 5 and 100 characters")
        String title,

        @NotBlank(message = "Message cannot be blank.")
        String message,

        @NotNull(message = "id of the course is required")
        Long cursoId,

        @NotNull(message = "The author ID is required")
        Long autorId,

        @NotNull(message = "The status is required")
        TopicStatus status
) {}

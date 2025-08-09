package com.aluracursos.backend.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseForm(
        @NotBlank(message = "Message cannot be blank.")
        String message,

        @NotNull(message = "Topic ID is required.")
        Long topicId,

        @NotNull(message = "Author ID is required.")
        Long authorId
) {
}
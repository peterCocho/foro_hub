package com.aluracursos.backend.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateForm(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull TopicStatus status
) {}

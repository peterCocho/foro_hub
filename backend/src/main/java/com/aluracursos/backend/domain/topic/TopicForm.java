package com.aluracursos.backend.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicForm(
        @NotBlank(message = "Title is required.")
        @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters.")
        String title,

        @NotBlank(message = "Message cannot be blank.")
        String message,

        @NotNull(message = "Course ID is required.")
        Long courseId,

        @NotNull(message = "Author ID is required.")
        Long authorId
) {}

package com.aluracursos.backend.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterResponse(

        @NotBlank(message = "The message cannot be blank")
        String message,

        @NotNull(message = "The id of the topic cannot be null")
        Long topicId
) {}

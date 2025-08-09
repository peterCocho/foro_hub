package com.aluracursos.backend.domain.response;

import jakarta.validation.constraints.NotNull;

public record DataUpdateResponse(

        @NotNull(message = "The response ID must be specified.")
        Long responseId,

        @NotNull(message = "It should be indicated whether the response was marked as a solution.")
        Boolean resolved
) {}

package com.aluracursos.backend.domain.response;

import jakarta.validation.constraints.NotNull;

public record ResponseUpdateForm(

        @NotNull(message = "Response ID must be specified")
        Long responseId,

        @NotNull(message = "Solution status must be indicated")
        Boolean isSolution
) {}

package com.aluracursos.backend.dominio.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoUpdateForm(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull TopicoStatus status
) {}

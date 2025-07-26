package com.aluracursos.backend.dominio.topico;

import java.time.LocalDateTime;

public record TopicoDetalleDTO(
        Long id,
        String titulo,
        String mensaje,
        TopicoStatus status,
        LocalDateTime fechaCreacion,
        Long autorId,
        Long cursoId
) { }


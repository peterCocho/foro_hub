package com.aluracursos.backend.dominio.topico;


import java.time.LocalDateTime;

public record TopicoListadoDTO(
        Long id,
        String titulo,
        LocalDateTime fechaCreacion,
        TopicoStatus status
) { }



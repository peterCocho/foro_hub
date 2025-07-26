package com.aluracursos.backend.dominio.respuesta;

import com.aluracursos.backend.dominio.topico.Topico;
import com.aluracursos.backend.dominio.topico.TopicoStatus;

public record TopicoRespuestaDTO(
        Long id,
        String titulo,
        String mensaje,
        TopicoStatus status,
        Long autorId,
        Long cursoId
) {
    public TopicoRespuestaDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutorId(),
                topico.getCursoId()
        );
    }
}

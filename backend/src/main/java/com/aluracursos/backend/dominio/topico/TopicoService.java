package com.aluracursos.backend.dominio.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // 🔹 Crear un nuevo tópico
    public Topico crearTopico(TopicoForm form) {
        var topico = new Topico(
                null,
                form.titulo(),
                form.mensaje(),
                TopicoStatus.ACTIVO, // Estado inicial
                LocalDateTime.now(),
                form.autorId(),
                form.cursoId()
        );
        return topicoRepository.save(topico);
    }

    public Topico guardar(Topico form) {
        var topico = new Topico(
                form.titulo(),
                form.mensaje(),
                form.autorId(),
                form.cursoId()
        );
        topico.setStatus(TopicoStatus.ACTIVO); // O usa form.status() si lo incluye

        return topicoRepository.save(topico);
    }


    // 🔹 Listar con paginación
    public Page<TopicoListadoDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion)
                .map(t -> new TopicoListadoDTO(
                        t.getId(),
                        t.getTitulo(),
                        t.getFechaCreacion(),
                        t.getStatus()
                ));
    }

    // 🔹 Obtener por ID
    public TopicoDetalleDTO obtenerDetallePorId(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException(id));

        return new TopicoDetalleDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getFechaCreacion(),
                topico.getAutorId(),
                topico.getCursoId()
        );
    }

    // 🔹 Actualizar por ID
    public Topico actualizarTopico(Long id, TopicoUpdateForm form) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException(id));

        topico.setTitulo(form.titulo());
        topico.setMensaje(form.mensaje());
        topico.setStatus(form.status());

        return topicoRepository.save(topico);
    }


}

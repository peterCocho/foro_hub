package com.aluracursos.backend.dominio.topico;

import com.aluracursos.backend.dominio.respuesta.TopicoRespuestaDTO;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<TopicoRespuestaDTO> registrarTopico(@RequestBody @Valid TopicoForm form) {
        Topico topico = new Topico(
                form.titulo(),
                form.mensaje(),
                form.autorId(),
                form.cursoId()
        );
        topico.setStatus(form.status());

        var topicoCreado = topicoService.guardar(topico);
        return ResponseEntity
                .created(URI.create("/topicos/" + topicoCreado.getId()))
                .body(new TopicoRespuestaDTO(topicoCreado));
    }


    // 🔹 Listar todos con paginación
    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> listarTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        var pagina = topicoService.listarTopicos
                (paginacion);
        return ResponseEntity.ok(pagina);
    }

    // 🔹 Obtener detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalleDTO> obtenerTopicoPorId(@PathVariable Long id) {
        var dto = topicoService.obtenerDetallePorId(id);
        return ResponseEntity.ok(dto);
    }
}

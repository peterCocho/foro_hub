package com.aluracursos.backend.dominio.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Topico.
 * Hereda métodos CRUD estándar como:
 * - findAll()
 * - findById(Long id)
 * - save(Topico topico)
 * - deleteById(Long id)
 */
@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // 🔍 Buscar por ID del curso (si quieres filtrar tópicos por curso)
    List<Topico> findByCursoId(Long cursoId);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

}

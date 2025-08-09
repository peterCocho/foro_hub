package com.aluracursos.backend.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Spring Data JPA creará automáticamente los métodos CRUD básicos por ti.
    // (findAll, findById, save, delete, etc.)
}
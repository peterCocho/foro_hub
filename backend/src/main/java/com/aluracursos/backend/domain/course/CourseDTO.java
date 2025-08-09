package com.aluracursos.backend.domain.course;

/**
 * DTO for exposing Course information safely through the API.
 */
public record CourseDTO(
        Long id,
        String name,
        String category
) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory().name());
    }
}
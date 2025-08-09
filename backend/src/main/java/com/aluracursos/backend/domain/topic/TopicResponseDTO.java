package com.aluracursos.backend.domain.topic;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        TopicStatus status,
        Long authorId,
        Long courseId
) {
    public TopicResponseDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getAuthor().getId(), // Get the ID from the related User entity
                topic.getCourse().getId()  // Get the ID from the related Course entity
        );
    }
}

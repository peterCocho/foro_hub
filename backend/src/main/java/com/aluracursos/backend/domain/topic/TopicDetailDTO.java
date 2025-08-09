package com.aluracursos.backend.domain.topic;

import java.time.LocalDateTime;

public record TopicDetailDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        TopicStatus status,
        String authorName,
        String courseName
) {
    /**
     * Constructor to create a DTO from a Topic entity.
     * @param topic The Topic entity from which to extract the data.
     */
    public TopicDetailDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getName(), // Get the name from the related User entity
                topic.getCourse().getName()  // Get the name from the related Course entity
        );
    }
}
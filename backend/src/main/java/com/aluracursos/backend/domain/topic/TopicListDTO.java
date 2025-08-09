package com.aluracursos.backend.domain.topic;

import java.time.LocalDateTime;

public record TopicListDTO(
        Long id,
        String title,
        LocalDateTime creationDate,
        TopicStatus status
) {
    /**
     *Constructor to map from the Topic entity.
     * @param topic The Topic entity to map from.
     */
    public TopicListDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getCreationDate(), topic.getStatus());
    }
}
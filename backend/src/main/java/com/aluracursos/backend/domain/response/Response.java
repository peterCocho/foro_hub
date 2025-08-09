package com.aluracursos.backend.domain.response;

import com.aluracursos.backend.domain.topic.Topic;
import com.aluracursos.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Response")
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "is_solution")
    private boolean isSolution = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Response(String message, Topic topic, User author) {
        this.message = message;
        this.topic = topic;
        this.author = author;
    }

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
}
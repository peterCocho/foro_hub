package com.aluracursos.backend.domain.topic;

import com.aluracursos.backend.domain.course.Course;
import com.aluracursos.backend.domain.response.Response;
import com.aluracursos.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor // Constructor sin argumentos requerido por JPA
@EqualsAndHashCode(of = "id") // Crea equals y hashCode basados solo en el ID
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TopicStatus status = TopicStatus.NO_RESPONSE; // Assuming enum values are also in English

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Relación con la respuesta que soluciona el tópico
    @OneToOne(fetch = FetchType.LAZY) // This could also be a ManyToOne if a response can solve multiple topics, but OneToOne is common.
    @JoinColumn(name = "solution_response_id")
    private Response solutionResponse;

    //  Se ejecuta antes de persistir si fechaCreacion está vacía
    @PrePersist
    public void prePersist() {
        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }
    }

    // Constructores
    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;
    }

    /**
     * Update the topic's data from a form.
     * * Only update the fields that are not null in the form.
     * * @param form The DTO with the new data.
     */
    public void updateData(TopicUpdateForm form) { // Assuming TopicUpdateForm is also refactored
        if (form.title() != null && !form.title().isBlank()) {
            this.title = form.title();
        }
        if (form.message() != null && !form.message().isBlank()) {
            this.message = form.message();
        }
        if (form.status() != null) {
            this.status = form.status();
        }
    }
}
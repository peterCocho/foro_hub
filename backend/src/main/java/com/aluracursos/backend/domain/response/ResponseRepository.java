package com.aluracursos.backend.domain.response;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    // Method to find all responses that are a solution (or not)
    List<Response> findByIsSolution(boolean isSolution);

    // Method to find responses by topic ID and whether they are the solution
    List<Response> findByTopicIdAndIsSolution(Long topicId, boolean isSolution);

    List<Response> findByTopicId(Long topicId);
}

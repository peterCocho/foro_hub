package com.aluracursos.backend.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Data access repository for the Topic entity.
 * Inherits standard CRUD methods like:
 * - findAll()
 * - findById(Long id)
 * - save(Topic topic)
 * - deleteById(Long id)
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    // 🔍 Find by course ID (to filter topics by course)
    List<Topic> findByCourseId(Long courseId);

    boolean existsByTitleAndMessage(String title, String message);

}

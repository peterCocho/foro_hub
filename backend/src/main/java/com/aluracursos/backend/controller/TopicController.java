package com.aluracursos.backend.controller;

import com.aluracursos.backend.domain.topic.*;
import com.aluracursos.backend.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDetailDTO> createTopic(
            @RequestBody @Valid TopicForm form,
            UriComponentsBuilder uriBuilder
    ) {
        // Creation logic is fully delegated to the service
        Topic createdTopic = topicService.createTopic(form);

        // Build the URI in a safe and Spring-recommended way
        URI uri = uriBuilder.path("/topics/{id}")
                .buildAndExpand(createdTopic.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new TopicDetailDTO(createdTopic));
    }

    // List all with pagination
    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> listTopics(@PageableDefault(size = 5) Pageable pagination) {
        Page<TopicListDTO> page = topicService.listTopics(pagination);
        return ResponseEntity.ok(page);
    }

    // Get details by ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> getTopicById(@PathVariable Long id) {
        TopicDetailDTO dto = topicService.getTopicDetailsById(id);
        return ResponseEntity.ok(dto);
    }

    // Update by ID
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDetailDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Topic updatedTopic = topicService.updateTopic(id, form);
        return ResponseEntity.ok(new TopicDetailDTO(updatedTopic));
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/spam")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> markAsSpam(@PathVariable Long id) {
        topicService.markTopicAsSpam(id);
        return ResponseEntity.noContent().build();
    }
}

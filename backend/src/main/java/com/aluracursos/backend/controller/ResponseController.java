package com.aluracursos.backend.controller;

import com.aluracursos.backend.domain.response.ResponseDTO;
import com.aluracursos.backend.domain.response.ResponseForm;
import com.aluracursos.backend.domain.topic.Topic;
import com.aluracursos.backend.domain.topic.TopicDetailDTO;
import com.aluracursos.backend.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    // create a new response
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDTO> createResponse(@RequestBody @Valid ResponseForm responseForm, UriComponentsBuilder uriBuilder) {
        ResponseDTO createdResponse = responseService.createResponse(responseForm);
        URI uri = uriBuilder.path("/responses/{id}").buildAndExpand(createdResponse.id()).toUri();
        return ResponseEntity.created(uri).body(createdResponse);
    }

    // List all the answers that are solutions, optionally filtered by topic.
    @GetMapping("/solutions")
    public ResponseEntity<List<ResponseDTO>> listSolutionResponses(@RequestParam(name = "topicId", required = false) Long topicId) {
        List<ResponseDTO> responses = responseService.listSolutionResponses(topicId);
        return ResponseEntity.ok(responses);
    }

    // Ark a new response as a solution for a specific topic.
    @PutMapping("/{responseId}/solution")
    @Transactional
    public ResponseEntity<TopicDetailDTO> markAsSolution(@PathVariable Long responseId) {
        Topic updatedTopic = responseService.markAsSolution(responseId);
        return ResponseEntity.ok(new TopicDetailDTO(updatedTopic));
    }

}
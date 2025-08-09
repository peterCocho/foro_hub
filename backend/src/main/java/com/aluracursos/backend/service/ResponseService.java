package com.aluracursos.backend.service;

import com.aluracursos.backend.domain.response.Response;
import com.aluracursos.backend.domain.response.ResponseDTO;
import com.aluracursos.backend.domain.response.ResponseForm;
import com.aluracursos.backend.domain.response.ResponseRepository;
import com.aluracursos.backend.domain.topic.Topic;
import com.aluracursos.backend.domain.topic.TopicRepository;
import com.aluracursos.backend.domain.topic.TopicStatus;
import com.aluracursos.backend.domain.user.User;
import com.aluracursos.backend.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseDTO createResponse(ResponseForm responseForm) {
        User author = userRepository.findById(responseForm.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + responseForm.authorId()));
        Topic topic = topicRepository.findById(responseForm.topicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + responseForm.topicId()));

        Response response = new Response(
                responseForm.message(),
                topic,
                author
        );

        responseRepository.save(response);
        return new ResponseDTO(response);
    }

    public List<ResponseDTO> listResponsesByTopic(Long topicId) {
        // Assuming you have a findByTopicId method in your ResponseRepository
        List<Response> responses = responseRepository.findByTopicId(topicId);
        return responses.stream()
                .map(ResponseDTO::new)
                .toList();
    }

    public List<ResponseDTO> listSolutionResponses(Long topicId) {
        List<Response> responses = responseRepository.findByTopicIdAndIsSolution(topicId, true);
        return responses.stream()
                .map(ResponseDTO::new)
                .toList();
    }

    @Transactional
    public Topic markAsSolution(Long responseId) {
        // 1. Find the response
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new EntityNotFoundException("Response not found with ID: " + responseId));

        Topic topic = response.getTopic();

        // 2. Validate that the topic is not already solved
        if (topic.getStatus() == TopicStatus.SOLVED) { // Assuming enum value is SOLVED
            throw new IllegalStateException("This topic has already been solved.");
        }

        // 3. Mark the response as the solution
        response.setSolution(true);

        // 4. Update the topic
        topic.setStatus(TopicStatus.SOLVED);
        topic.setSolutionResponse(response); // Associates the solution response with the topic

        return topic;
    }
}
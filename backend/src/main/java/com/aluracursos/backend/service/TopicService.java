package com.aluracursos.backend.service;

import com.aluracursos.backend.domain.course.Course;
import com.aluracursos.backend.domain.course.CourseRepository;
import com.aluracursos.backend.domain.user.User;
import com.aluracursos.backend.domain.user.UserRepository;
import com.aluracursos.backend.domain.topic.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository,
                        UserRepository userRepository,
                        CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    // Create a new topic
    public Topic createTopic(TopicForm topicForm) {
        // Check for duplicates before creating to ensure data integrity
        if (topicRepository.existsByTitleAndMessage(topicForm.title(), topicForm.message())) {
            throw new IllegalStateException("A topic with the same title and message already exists.");
        }

        // 1. Fetch the related entities from the database using their IDs.
        User author = userRepository.findById(topicForm.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + topicForm.authorId()));
        Course course = courseRepository.findById(topicForm.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + topicForm.courseId()));

        // 2. Create the new Topic with the full entities, not just the IDs.
        Topic topic = new Topic(
                topicForm.title(),
                topicForm.message(),
                author,
                course
        );
        return topicRepository.save(topic);
    }


    // List topics with pagination
    public Page<TopicListDTO> listTopics(Pageable pageable) {
        // The mapping logic is now in the DTO, making the service much cleaner.
        return topicRepository.findAll(pageable).map(TopicListDTO::new);
    }

    // Get details by ID
    public TopicDetailDTO getTopicDetailsById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));
        
        // We simply pass the full entity to the DTO's constructor.
        return new TopicDetailDTO(topic);
    }

    // Update by ID
    public Topic updateTopic(Long id, TopicUpdateForm updateForm) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));

        // It's a good practice to delegate the update logic to a method within the entity itself.
        topic.updateData(updateForm);

        return topicRepository.save(topic);
    }

    // Delete by ID (Hard Delete)
    public void deleteTopic(Long id) {
      Topic topic = topicRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));

      topicRepository.delete(topic);
    }
    
    public void markTopicAsSpam(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));
        topic.setStatus(TopicStatus.SPAM);
        topicRepository.save(topic);
    }

}

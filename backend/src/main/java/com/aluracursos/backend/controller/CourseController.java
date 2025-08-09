package com.aluracursos.backend.controller;

import com.aluracursos.backend.domain.course.Course;
import com.aluracursos.backend.domain.course.CourseDTO;
import com.aluracursos.backend.domain.course.CourseRepository;
import com.aluracursos.backend.domain.course.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> courseDTOs = courses.stream().map(CourseDTO::new).toList();
        return ResponseEntity.ok(courseDTOs);
    }
}
package com.example.studentcourse.controller;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.service.CourseService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    private static final Logger log =LoggerFactory.getLogger(CourseController.class);

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid Course course) {
        log.info("Received request to create course: {}", course);
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping
    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        return courseService.getAllCourses();
    }
//added
    @GetMapping("/more-than/{minStudents}")
    public List<Course> findCoursesWithMoreThanNStudents(@PathVariable int minStudents) {
        return courseService.findCoursesWithMoreThanNStudents(minStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        log.info("Fetching course by course id: {}",id);
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("Received request to delete course: {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
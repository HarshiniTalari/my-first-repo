package com.example.studentcourse.service;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
    //added
    public List<Course> findCoursesWithMoreThanNStudents(int minStudents) {
        return courseRepository.findCoursesWithMoreThanNStudents(minStudents);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
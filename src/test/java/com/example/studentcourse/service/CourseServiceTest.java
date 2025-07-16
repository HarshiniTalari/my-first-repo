package com.example.studentcourse.service;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    public StudentRepository studentRepository;
    @Mock
    public CourseRepository courseRepository;
    @InjectMocks
    public CourseService courseService;

    private Student student;
    private Course course;

    @BeforeEach
    void setUp(){
        student = new Student();
        student.setId(1L);
        student.setName("Harshi");
        student.setEmail("harshi@email.com");

        course = new Course();
        course.setId(101L);
        course.setName("Java");
        course.setDescription("intro of java");
    }
    @Test
    void testCreateCourse(){
        when(courseRepository.save(course)).thenReturn(course);
        Course savedCourse = courseService.createCourse(course);
        assertNotNull(savedCourse);
        assertEquals("Java", savedCourse.getName());
        verify(courseRepository, times(1)).save(course);
    }
    @Test
    void testGetAllCourses(){
        List<Course> mockCourses = Collections.singletonList(course);
        when(courseRepository.findAll()).thenReturn(mockCourses);
        List<Course> result = courseService.getAllCourses();
        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals("Java",result.get(0).getName());
        verify(courseRepository, times(1)).findAll();
    }
    @Test
    void testGetCourseById(){
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course result = courseService.getCourseById(1L);
        assertNotNull(result);
        assertEquals("Java", result.getName());
        verify(courseRepository,times(1)).findById(1L);
    }
}

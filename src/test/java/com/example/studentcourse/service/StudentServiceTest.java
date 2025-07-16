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


@ExtendWith(MockitoExtension.class )
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private StudentService studentService;

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
        course.setName("java");
        course.setDescription("intro of java");
    }
//    This test uses the typical unit test structure:
//             Arrange → Act → Assert → Verify
    @Test
    void testCreateStudent(){
        when(studentRepository.save(student)).thenReturn(student);//Arrange
        Student savedStudent = studentService.createStudent(student);//Act/Preparation
        assertNotNull(savedStudent);//assert
        assertEquals("Harshi",savedStudent.getName());
        verify(studentRepository, times(1)).save(student);//Verify
    }
    @Test
    void testGetAllStudents(){
        List<Student> mockStudents = Collections.singletonList(student);
        when(studentRepository.findAll()).thenReturn(mockStudents);
        List<Student> result = studentService.getAllStudents();
        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals("Harshi", result.get(0).getName());
        verify(studentRepository, times(1)).findAll();
    }
    @Test
    void testGetStudentById(){
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById(1L);
        assertNotNull(result);
        assertEquals("Harshi",result.getName());
        verify(studentRepository,times(1)).findById(1L);

    }
}

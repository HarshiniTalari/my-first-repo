package com.example.studentcourse.controller;

import com.example.studentcourse.model.Student;
import com.example.studentcourse.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student) {
        log.info("Received request to create student: {}", student);
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        log.info("Enrolling student with ID {} into course with ID {}", studentId, courseId);
        return ResponseEntity.ok(studentService.enrollStudentToCourse(studentId, courseId));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return studentService.getAllStudents();
    }
//added
    @GetMapping("/by-course")
    public List<Student> getStudentsByCourseName(@RequestParam String courseName) {
        log.info("Fetching students by course name: {}", courseName);
        return studentService.getStudentsByCourseName(courseName);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("Fetching student with ID {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("Deleting student with ID {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updatestudents")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody  @Valid Student updateStudent){
        log.info("Updating student with ID {}: new data: {}", id, updateStudent);
        Student student = studentService.updateStudentById(id, updateStudent);
        return ResponseEntity.ok(student);
    }
//    @GetMapping("/by-course")
//    public List<Student> getStudentsByCourseName(@RequestParam String courseName) {
//        log.info("Fetching students by course name: {}", courseName);
//        return studentService.getStudentsByCourseName(courseName);
//
//    }
}
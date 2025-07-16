package com.example.studentcourse.service;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        try {
            return studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
        }
        catch (Exception e){
            throw new RuntimeException("Failed to get student by ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student enrollStudentToCourse(Long studentId, Long courseId) {
        try {
            Student student = getStudentById(studentId);
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            student.getCourses().add(course);
            return studentRepository.save(student);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to enroll student " + studentId + " to course " + courseId + ": " + e.getMessage(), e);
        }
    }

    public Student updateStudentById(Long id, Student updateStudent){
        try {
            Student existingStudent = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("student not found"));

            existingStudent.setName(updateStudent.getName());
            existingStudent.setEmail(updateStudent.getEmail());

            return studentRepository.save(existingStudent);
        }
        catch (Exception e){
            throw new RuntimeException ("failed to update student with id" + id + ": " + e.getMessage());
        }
    }
    //added
    public List<Student> getStudentsByCourseName(String courseName) {
        return studentRepository.findStudentsByCourseName(courseName);
    }
}
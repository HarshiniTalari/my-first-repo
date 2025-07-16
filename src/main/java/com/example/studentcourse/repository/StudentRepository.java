package com.example.studentcourse.repository;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    // JPQL Query
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name = :courseName")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);

//    @Query(value = "SELECT COUNT(*) FROM student_courses WHERE course_id = :courseId", nativeQuery = true)
//    int countStudentsInCourse(@Param("courseId") Long courseId);
//
//    @Query("SELECT c FROM Course c WHERE SIZE(c.students) > :minStudents")
//    List<Course> findCoursesWithMoreThanNStudents(@Param("minStudents") int minStudents);


}

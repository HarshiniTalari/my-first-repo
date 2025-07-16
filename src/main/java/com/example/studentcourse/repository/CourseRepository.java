package com.example.studentcourse.repository;

import com.example.studentcourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE SIZE(c.students) > :minStudents")
    List<Course> findCoursesWithMoreThanNStudents(@Param("minStudents") int minStudents);
}

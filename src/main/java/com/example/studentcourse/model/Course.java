package com.example.studentcourse.model;


import com.example.studentcourse.repository.CourseRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "course id required")
    private Long id;
    @NotBlank(message = "course name shouldn't be blank")
    private String name;
    @NotNull(message = "description cannot be null")
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }

}
package com.example.studentcourse.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "student id required")
    private Long id;
    @NotBlank(message = "student name shouldn't be blank")
    @Size(min=5, max=50)
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    @Column(unique = true)
    private String email;

    @ManyToMany//join table
    private Set<Course> courses = new HashSet<>();

    public Student() {}

    public Student(Long id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}

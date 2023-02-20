package com.fortytwo42.student.rest.api.assignment.services;

import com.fortytwo42.student.rest.api.assignment.entities.Student;

import java.util.Optional;

public interface StudentService {
    Optional<Student> findStudentByName(String name);
}

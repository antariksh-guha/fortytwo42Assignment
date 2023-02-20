package com.fortytwo42.student.rest.api.assignment.repositories;

import com.fortytwo42.student.rest.api.assignment.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByName(String name);
}

package com.fortytwo42.student.rest.api.assignment.repositories;

import com.fortytwo42.student.rest.api.assignment.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}

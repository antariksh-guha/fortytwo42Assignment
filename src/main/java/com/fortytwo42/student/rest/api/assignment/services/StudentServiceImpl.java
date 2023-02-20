package com.fortytwo42.student.rest.api.assignment.services;

import com.fortytwo42.student.rest.api.assignment.entities.Student;
import com.fortytwo42.student.rest.api.assignment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> findStudentByName(String name) {
        return studentRepository.findByName(name);
    }
}

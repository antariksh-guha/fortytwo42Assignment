package com.fortytwo42.student.rest.api.assignment.controller;

import com.fortytwo42.student.rest.api.assignment.constants.Constants;
import com.fortytwo42.student.rest.api.assignment.entities.Student;
import com.fortytwo42.student.rest.api.assignment.exceptions.StudentNotFoundException;
import com.fortytwo42.student.rest.api.assignment.services.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = Constants.STUDENT_API_ENDPOINT)
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping(Constants.GET_STUDENT_API)
    public ResponseEntity<Student> getStudentByName(@RequestParam String name) {
        Student student = null;
        log.info("getStudentByName() called with name: {}", name);

        try {
            student = studentService.findStudentByName(name).orElseThrow(() -> new StudentNotFoundException("Student not found with name : " + name));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        log.info("getStudentByName() returned : {}", student);

        return ResponseEntity.ok().body(student);
    }
}

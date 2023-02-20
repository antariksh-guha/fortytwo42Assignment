package com.fortytwo42.student.rest.api.assignment.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortytwo42.student.rest.api.assignment.AssignmentApplication;
import com.fortytwo42.student.rest.api.assignment.entities.Student;
import com.fortytwo42.student.rest.api.assignment.repositories.StudentRepository;
import com.fortytwo42.student.rest.api.assignment.utils.FixtureHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AssignmentApplication.class})
@DirtiesContext
@ActiveProfiles("test")
public class StudentServiceTest {

    private static final String FIXTURES = "fixtures";
    @InjectMocks
    private StudentServiceImpl studentService;
    @MockBean
    private StudentRepository studentRepository;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void simpleServiceTest() throws IOException {
        when(studentRepository.findByName(any(String.class)))
                .thenReturn(Optional.ofNullable(loadSampleResponseList()));

        Optional<Student> student = studentService
                .findStudentByName("student1");

        verify(studentRepository, times(1))
                .findByName(any(String.class));

        assertNotNull(student.get());
    }

    private Student loadSampleResponseList() throws IOException {
        String fixture = FixtureHelpers.fixture(FIXTURES + "/response1.json");
        return objectMapper.readValue(fixture, new TypeReference<Student>() {});
    }
}

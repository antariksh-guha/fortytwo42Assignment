package com.fortytwo42.student.rest.api.assignment.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortytwo42.student.rest.api.assignment.AssignmentApplication;
import com.fortytwo42.student.rest.api.assignment.constants.Constants;
import com.fortytwo42.student.rest.api.assignment.entities.Student;
import com.fortytwo42.student.rest.api.assignment.services.StudentService;
import com.fortytwo42.student.rest.api.assignment.utils.FixtureHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = {AssignmentApplication.class})
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class StudentControllerTest {

    private static final String FIXTURES = "fixtures";

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void simpleControllerTest() throws Exception {
        Student sampleResponse = loadSampleResponseList();
        assertNotNull(sampleResponse);

        when(studentService
                .findStudentByName(any(String.class)))
                .thenReturn(Optional.of(sampleResponse));

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);

        URI uri = UriComponentsBuilder
                .fromPath(Constants.STUDENT_API_ENDPOINT + Constants.GET_STUDENT_API)
                .queryParam("name", "student1")
                .build().toUri();

        assertEquals(Constants.STUDENT_API_ENDPOINT + Constants.GET_STUDENT_API + "?name=student1", uri.toString());

        MockHttpServletResponse response = mvc.perform(
                get(uri.toString()).accept(APPLICATION_JSON_UTF8)
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(studentService, times(1))
                .findStudentByName(argumentCaptor.capture());

        assertEquals("student1", argumentCaptor.getValue().toString());
    }

    private Student loadSampleResponseList() throws IOException {
        String fixture = FixtureHelpers.fixture(FIXTURES + "/response1.json");
        return objectMapper.readValue(fixture, new TypeReference<Student>() {});
    }
}

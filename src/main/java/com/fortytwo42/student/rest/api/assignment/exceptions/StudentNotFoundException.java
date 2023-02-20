package com.fortytwo42.student.rest.api.assignment.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException()
    {
        super();
    }
    public StudentNotFoundException(String msg)
    {
        super(msg);
    }

}

package com.fortytwo42.student.rest.api.assignment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @JsonIgnore
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer marks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Student student;
}


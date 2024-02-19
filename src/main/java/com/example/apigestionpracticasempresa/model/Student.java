package com.example.apigestionpracticasempresa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentid;
    private String firstname;
    private String lastname;
    private String password;
    private String dni;
    private LocalDate dateofbirth;
    private String email;
    private String contactphone;
    private Integer totaldualhours;
    private Integer totalfcthours;
    private String observations;

    @OneToMany(mappedBy = "student")
    private List<Activity> diaryactivities;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "tutor")
    private Teacher tutor;
}

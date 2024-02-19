package com.example.apigestionpracticasempresa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityid;

    private LocalDate activitydate;
    @Enumerated(EnumType.STRING)
    private PracticeType practicetype;
    private Integer totalhours;
    private String activitydescription;
    private String activityobservations;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;
}

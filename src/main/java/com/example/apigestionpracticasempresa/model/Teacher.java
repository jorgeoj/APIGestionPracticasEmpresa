package com.example.apigestionpracticasempresa.model;

import com.example.apigestionpracticasempresa.model.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherid;

    private String teacherfirstname;
    private String teacherlastname;
    private String teacherpassword;
    private String teacheremail;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<Student> students;
}

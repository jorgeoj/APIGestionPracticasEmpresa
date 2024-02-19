package com.example.apigestionpracticasempresa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyid;
    private String companyname;
    private String phonenumber;
    private String email;
    private String companycontact;
    private String incidents;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Student> student;
}

package com.example.apigestionpracticasempresa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idactividad;
    private Date fecha;
    private String observacion;
    private Integer horas;
    private String tipo;
    private String actividad;
    @ManyToOne
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno")
    private Alumno alumno;
}

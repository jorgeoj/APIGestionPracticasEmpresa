package com.example.apigestionpracticasempresa.repositories;

import com.example.apigestionpracticasempresa.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    /**
     * Verifica si existe un alumno por su email
     * @param email del alumno
     * @return Si el alumno con el email concreto existe o no
     */
    public Boolean existsAlumnoByEmail(String email);
    /**
     * Obtiene al alumno por su email
     * @param email del alumno
     * @return El alumno que coincide con el email
     */
    public Alumno getAlumnoByEmail(String email);
}

package com.example.apigestionpracticasempresa;

import com.example.apigestionpracticasempresa.model.Activity;
import com.example.apigestionpracticasempresa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // TODO Saca todos los datos del estudiante por el email
    public Student getStudentByEmail(String email);

    // TODO Repasar si esto esta bien (sacar todas las actividades del estudiante activo)
    @Query("SELECT a from Activity a WHERE a.student.studentid =: idStudent")
    public List<Activity> getActivitiesByStudent(@Param("idStudent") Long idStudent);
}

package com.example.apigestionpracticasempresa.controllers;

import com.example.apigestionpracticasempresa.model.Actividad;
import com.example.apigestionpracticasempresa.model.Alumno;
import com.example.apigestionpracticasempresa.repositories.ActividadRepository;
import com.example.apigestionpracticasempresa.repositories.AlumnoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AlumnoWebController {

    // TODO Mirar los html por si se quieren cambiar cosas
    // TODO hacer html editarActividad
    // TODO Comprobar que funcionan las cosas

    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/")
    public String volverLogin(){
        return "redirect:/login";
    }

    // TODO Comprobar si esta bien
    @GetMapping("/{idalumno}")
    public String getActividadesByAlumno(@PathVariable Long idalumno, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Alumno alumnoSession = (Alumno) session.getAttribute("alumno");

        if(alumnoSession!=null){
            Alumno alumno2 = alumnoSession;
            model.addAttribute("actividades",actividadRepository.getAllByAlumno(alumno2));
            System.out.println(actividadRepository.getAllByAlumno(alumno2));
            return "mainViewAlumno";
        }else{
            model.addAttribute("alumno",new Alumno());
            return "login";
        }
    }

    // TODO Probar si funciona
    @PostMapping("/new")
    public String newActividad(@ModelAttribute Actividad actividad, HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        System.out.println(actividad);

        if(alumno!= null && (!(actividad.getActividad().equals("")) && !(actividad.getFecha().equals(""))) &&
                actividad.getTipo() !=null && actividad.getHoras() != null){
            System.out.println(actividad);
            actividad.setAlumno(alumno);
            actividadRepository.save(actividad);
            return "redirect:/"+Long.valueOf( actividad.getAlumno().getIdalumno()) ;
        }else if(alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "login";
        } else if (actividad.getActividad().equals("") || actividad.getFecha().equals("") ||
                actividad.getTipo() == null || actividad.getHoras() == null) {
            return "redirect:/new";
        } else{
            return "";
        }
    }

    // TODO Probar si funciona
    @GetMapping("/new")
    public String newActividad(Model model,HttpServletRequest request){
        Actividad actividad = new Actividad();

        HttpSession session=request.getSession();
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if(alumno != null){
            actividad.setAlumno(alumno);
            model.addAttribute("actividad",actividad);
            return "editarActividad";
        }else{
            model.addAttribute("alumno",new Alumno());
            return "login";
        }
    }

    // TODO Probar si funciona
    @GetMapping("/edit/{idActividad}")
    public String editarActividad(@PathVariable Long idActividad, Model model, HttpServletRequest request){
        HttpSession session=request.getSession();
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if(alumno==null){
            model.addAttribute("alumno",new Alumno());
            return "login";
        }else{

            model.addAttribute("actividad",actividadRepository.findById(idActividad).get());
            return "editarActividad";
        }

    }

    // TODO Probar si funciona
    @PostMapping("/edit/{idActividad}")
    public String editActivityPost(@PathVariable Long idActividad, @ModelAttribute Actividad actividad,HttpServletRequest request,Model model){
        HttpSession sesion=request.getSession();
        Alumno alumno = (Alumno) sesion.getAttribute("alumno");
        if(alumno != null && !actividad.getActividad().equals("") && !actividad.getFecha().equals("")
                && actividad.getHoras() != null && actividad.getTipo() != null){
            actividad.setAlumno(alumno);
            actividadRepository.save(actividad);
            return "redirect:/"+Long.valueOf( (alumno).getIdalumno());
        }else if(alumno==null){
            model.addAttribute("alumno",new Alumno());
            return "login";
        } else if(actividad.getActividad().equals("") || actividad.getFecha().equals("") ||
                actividad.getTipo() == null || actividad.getHoras() == null){
            return "redirect:/edit/"+actividad.getIdactividad();
        } else{
            return "";
        }
    }

    // TODO Probar si funciona
    @GetMapping("/login")
    public String getLogin(Model modelo){
        modelo.addAttribute("alumno",new Alumno());
        return "login";
    }

    // TODO Probar si funciona
    @PostMapping("/succesfull")
    public String getAlumno(@ModelAttribute Alumno alumno, HttpServletRequest request){
        Boolean existencia = alumnoRepository.existsAlumnoByEmail(alumno.getEmail());
        System.out.println(existencia.toString());
        if(existencia){
            Alumno alumnoBBDD = alumnoRepository.getAlumnoByEmail(alumno.getEmail());
            if(alumnoBBDD.getContrasenya().equals(alumno.getContrasenya())){
                HttpSession s = request.getSession();
                s.setAttribute("alumno",alumnoBBDD);
                return "redirect:/"+ alumnoBBDD.getIdalumno();
            }else{
                return "redirect:/login";
            }
        }else{
            return "redirect:/login";
        }
    }

    // TODO Probar si funciona
    @PostMapping("/borrarActividad/{id}")
    public String borrarActividad(@PathVariable Long id, HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        Alumno alumno = (Alumno) session.getAttribute("alumno");

        if(alumno != null && id != null){
            Actividad actividad = actividadRepository.findById(id).get();
            actividadRepository.delete(actividad);
            return "redirect:/"+alumno.getIdalumno();
        } else if (alumno == null) {
            model.addAttribute("alumno",new Alumno());
            return "login";
        } else{
            Actividad actividad = new Actividad();
            actividad.setAlumno(alumno);
            model.addAttribute("actividad",actividad);
            return "editarActividad";
        }


    }

    // TODO Probar si funciona
    @GetMapping("logout")
    public String logout(Model model, HttpServletRequest request)  {
        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("alumno",new Alumno());
        return "login";

    }
}

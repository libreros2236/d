package com.example.baner.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.baner.model.Alumno;
import com.example.baner.model.Calificacion;
import com.example.baner.model.Curso;
import com.example.baner.model.CursoAlumno;
import com.example.baner.model.Expediente;
import com.example.baner.repository.AlumnoRepository;
import com.example.baner.repository.CalificacionRepository;
import com.example.baner.repository.CursoAlumnoRepository;
import com.example.baner.repository.CursoRepository;
import com.example.baner.repository.ExpedienteRepository;

@Controller
@RequestMapping("/Alumno/")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnorepository;
	
	@Autowired
	private ExpedienteRepository expedienterepository;
	
	@Autowired
	private CursoAlumnoRepository cursoAlumnoRepository;
	
	@Autowired
	private CursoRepository cursorepository;
	
	@Autowired
	private CalificacionRepository calificacionrepository;
	
	private Alumno alumno;
	private Expediente expediente;
	
	@GetMapping("InicioAlumno")
	public String Inicio(Model model) {
		model.addAttribute("Alumnos",alumnorepository.findAll());
		return "IndexAlumno";
	}
	
	@GetMapping("AgregaAlumno")
    public String AgregaAlumno() {
        return "AgregaAlumno";
    }
	
	@PostMapping("Agrega") 
	public String crearAlumno(@Valid Alumno alumnos,Model model) {
		 
		//@Valid @RequestBody Alumno alumnos
		Expediente expediente=new Expediente();
		alumnos.setExpediente(expediente);
		expedienterepository.save(expediente);
		alumnorepository.save(alumnos);
		return "redirect:InicioAlumno";	
	}
	
	@GetMapping("EliminaAlumno/{id}")
	public String EliminaAlumno(@PathVariable(value="id")int matricula,Model model) {
		Calificacion calificacion;
		try {
			alumno=alumnorepository.findById(matricula).get();
			Iterable<CursoAlumno> Coleccion=cursoAlumnoRepository.findAll();
			Iterator<CursoAlumno> Coleccion1=Coleccion.iterator();
			while(Coleccion1.hasNext()) {
				CursoAlumno cursosAlumno=Coleccion1.next();
				if(cursosAlumno.getId().getMatricula()==alumno.getMatricula()) {
					calificacion=calificacionrepository.findById(cursosAlumno.getCalificacion().getIdCalifica()).get();
					cursoAlumnoRepository.delete(cursosAlumno);
					calificacionrepository.delete(calificacion);
					Coleccion1.remove();
				}
			}			
			expediente=expedienterepository.findById(alumno.getExpediente().getIdexpediente()).get();
			alumnorepository.delete(alumno);
			expedienterepository.delete(expediente);
			model.addAttribute("Alumnos",alumnorepository.findAll());
			return "Index";
		}catch(Exception e) {}		
		model.addAttribute("Alumnos",alumnorepository.findAll());
		return "IndexAlumno";
	}
	
	@GetMapping("/Alumnos/{id}")
	public Alumno ObtenAlumnoMat(@PathVariable(value="id") int matricula){
		
		try {
			alumno=alumnorepository.findById(matricula).get();
			return alumno;
		}catch(Exception e) {
		}
		return null;
	}
	
	@GetMapping("EditaAlumno/{id}")
	public String EditaAlumnos(@PathVariable(value="id") int matricula,Model model){
			alumno=alumnorepository.findById(matricula).orElseThrow(()->new IllegalArgumentException("Invalid student Id:" + matricula));
			model.addAttribute("AlumnoActu",alumno);
			return "ActualizaAlumno";
	}
	
	@PostMapping("ActualizaAlumno/{id}")
	public String ActualizaAlumno(@PathVariable(value="id") int matricula,@Valid Alumno alumnos,Model model){
			alumno=alumnorepository.findById(matricula).get();
			alumno.setNombre(alumnos.getNombre());
			alumnorepository.save(alumno);
			model.addAttribute("Alumnos",alumnorepository.findAll());
			return "IndexAlumno";	
	}
	
	@GetMapping("EditaExpediente/{id}")
	public String EditaExpediente(@PathVariable(value="id") int matricula,Model model){
			alumno=alumnorepository.findById(matricula).orElseThrow(()->new IllegalArgumentException("Invalid student Id:" + matricula));
			model.addAttribute("AlumnoActu",alumno.getExpediente());
			return "ActualizaExpediente";
	}
	
	@PostMapping("ActualizaExpediente/{id}")
	public String ActualizaExpediente(@PathVariable(value="id") int matricula,@Valid Expediente expe,Model model){
			Expediente exped=expedienterepository.findById(matricula).get();
			exped.setDescripcion(expe.getDescripcion());
			expedienterepository.save(exped);
			model.addAttribute("Alumnos",alumnorepository.findAll());
			return "IndexAlumno";	
	}
	

}

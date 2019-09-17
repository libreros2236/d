package com.example.baner.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baner.model.Alumno;
import com.example.baner.model.Calificacion;
import com.example.baner.model.Curso;
import com.example.baner.model.CursoAlumno;
import com.example.baner.model.Expediente;
import com.example.baner.model.Profesor;
import com.example.baner.repository.AlumnoRepository;
import com.example.baner.repository.CalificacionRepository;
import com.example.baner.repository.CursoAlumnoRepository;
import com.example.baner.repository.CursoRepository;
import com.example.baner.repository.ProfesorRepository;

@Controller
@RequestMapping("/Cursos/")
public class CursoController {
	
	@Autowired
	private CursoRepository cursorepository;
	
	@Autowired
	private ProfesorRepository profesorrepository;
	
	@Autowired
	private AlumnoRepository alumnorepository;
	
	@Autowired
	private CursoAlumnoRepository cursoAlumnoRepository;
	

	@Autowired
	private CalificacionRepository calificacionrepository;
	
	private Curso curso;
	
	
	
	@GetMapping("Lista/{idC}")
	public String Lista(@PathVariable(value="idC")int RFC,Model model) {
		
		Iterable<CursoAlumno> Coleccion=cursoAlumnoRepository.findAll();
		Iterator<CursoAlumno> Coleccion1=Coleccion.iterator();
		
		Iterable<Profesor> Coleccion2=profesorrepository.findAll();
		Iterator<Profesor> Coleccion3=Coleccion2.iterator();
		
		List<CursoAlumno> aux=new ArrayList<CursoAlumno>();
		List<Profesor> aux1=new ArrayList<Profesor>();
		
		while(Coleccion1.hasNext()) {
			CursoAlumno cursosAlumno=Coleccion1.next();
			if(cursosAlumno.getId().getRFC()==RFC) 
				aux.add(cursosAlumno);
		}
		
		while(Coleccion3.hasNext()) {
			Profesor Pro=Coleccion3.next();
			if(Pro.getCurso()!=null)
				if(Pro.getCurso().getRFC()==RFC) 
					aux1.add(Pro);
		}
		
		model.addAttribute("Profesors",aux1);
		model.addAttribute("Alumnos",aux);
		
		return "ListaTotal";
	}
	
	@GetMapping("AgregaAlumnoCurso/{idC}")
	public String AgregaAlumnoCurso(@PathVariable(value="idC")int RFC,Model model) {
		model.addAttribute("Materia", RFC);
		model.addAttribute("Alumnos",alumnorepository.findAll());
		return "AgregaAlumnoCurso";
	}
	
	@GetMapping("AgregaAlumnoCursoF/{idC}/{id}")
	public String AgregaAlumnoCursoF(@PathVariable(value="idC")int RFC,@PathVariable(value="id")int matricula,Model model) {
		Calificacion calificacion=new Calificacion();
		Alumno alumno=alumnorepository.findById(matricula).get();
		curso=cursorepository.findById(RFC).get();
		CursoAlumno AlumnoAltaCurso=new CursoAlumno(alumno,curso,calificacion);
		calificacionrepository.save(calificacion);
		cursoAlumnoRepository.save(AlumnoAltaCurso);
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";	
	}
	
	@GetMapping("AgregaProfesorCurso/{idC}")
	public String AgregaProfesorCurso(@PathVariable(value="idC")int RFC,Model model) {
		model.addAttribute("Materia", RFC);
		model.addAttribute("Profesors",profesorrepository.findAll());
		return "AgregaProfesorCurso";
	}
	
	@GetMapping("AgregaProfesorCursoF/{idC}/{id}")
	public String AgregaProfesorCursoF(@PathVariable(value="idC")int RFC,@PathVariable(value="id")int matricula,Model model) {
		curso=cursorepository.findById(RFC).get();
		Profesor profesor=profesorrepository.findById(matricula).get();
		curso.getProfesor().add(profesor);
		profesor.setCurso(curso);
		cursorepository.save(curso);
		profesorrepository.save(profesor);
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";	
	}
	
	
	@GetMapping("InicioCurso")
	public String InicioCurso(Model model) {
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";
	}
	
	@GetMapping("AgregaCurso")
    public String AgregaCurso() {
        return "AgregaCurso";
    }
	
	@PostMapping("AgregaCursos") 
	public String crearCurso(@Valid Curso cursos,Model model) {
		cursorepository.save(cursos);
		return "redirect:InicioCurso";	
	}
	
	@GetMapping("EditaCurso/{id}")
	public String EditaCurso(@PathVariable(value="id") int RFC,Model model){
			curso=cursorepository.findById(RFC).orElseThrow(()->new IllegalArgumentException("Invalid student Id:" + RFC));
			model.addAttribute("CursoActu",curso);
			return "ActualizaCurso";
	}
	
	@PostMapping("ActualizaCurso/{id}")
	public String ActualizaCurso(@PathVariable(value="id") int RFC,@Valid Curso cursos,Model model){
			curso=cursorepository.findById(RFC).get();
			curso.setNombre(cursos.getNombre());
			cursorepository.save(curso);
			model.addAttribute("Cursos",cursorepository.findAll());
			return "IndexCurso";	
	}
		
	
	
	@GetMapping("/Cursos/{id}")
	public Curso ObtenAlumnoMat(@PathVariable(value="id") int RFC){
		
		try {
			curso=cursorepository.findById(RFC).get();
			return curso;
		}catch(Exception e) {			
		}
		return null;
	}
	
	
	@PutMapping("/EditaCurso/{id}")
	public void EditaCurso(@PathVariable(value="id") int RFC,@Valid @RequestBody Curso cursos){
			curso=cursorepository.findById(RFC).get();
			curso.setNombre(curso.getNombre());
			cursorepository.save(curso);
	}
	
	
	@PostMapping("/CursoAltaProfe/{idC}/{idP}")
	public void ActualizaProfe(@PathVariable(value="idC")int Curso,@PathVariable(value="idP")int matricula){		
		curso=cursorepository.findById(Curso).get();
		Profesor profesor=profesorrepository.findById(matricula).get();
		curso.getProfesor().add(profesor);
		profesor.setCurso(curso);
		cursorepository.save(curso);
		profesorrepository.save(profesor);
	}
	
	@GetMapping("BajaAlumno/{id}/{idC}")
	public String BajaAlumno(@PathVariable(value="id")int matricula,@PathVariable(value="idC")int RFC,Model model) {
		int band=0;
		CursoAlumno cursosAlumno=null;
		Calificacion calificacion;
		Iterable<CursoAlumno> Coleccion=cursoAlumnoRepository.findAll();
		Iterator<CursoAlumno> Coleccion1=Coleccion.iterator();
		
		while(Coleccion1.hasNext() && band==0) {
			cursosAlumno=Coleccion1.next();
			if(cursosAlumno.getId().getRFC()==RFC && cursosAlumno.getId().getMatricula()==matricula) 
				band=1;
				
		}
		
		calificacion=cursosAlumno.getCalificacion();
		cursoAlumnoRepository.delete(cursosAlumno);
		calificacionrepository.delete(calificacion);
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";
	}
	
	@GetMapping("EC/{id}")
	public String EC(@PathVariable(value="id")int idmateria,Model model) {
		/*int band=0;
		CursoAlumno cursosAlumno=null;
		Iterable<CursoAlumno> Coleccion=cursoAlumnoRepository.findAll();
		Iterator<CursoAlumno> Coleccion1=Coleccion.iterator();
		
		while(Coleccion1.hasNext() && band==0) {
			cursosAlumno=Coleccion1.next();
			if(cursosAlumno.getId().getRFC()==RFC && cursosAlumno.getId().getMatricula()==matricula) 
				band=1;
				
		}*/
		
		
		model.addAttribute("idmateria",idmateria);
		
		return "ActualizaCalificacion";
	}
	
	@PostMapping("EditaCalificacion/{Cali}")
	public String EditaCalificacion(@PathVariable(value="Cali")int matricula,@Valid Calificacion calificacion,Model model) {
	
		Calificacion calif=calificacionrepository.findById(matricula).get();
		calif.setCalifi(calificacion.getCalifi());
		calificacionrepository.save(calif);
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";
	}
	
	
	
	@GetMapping("BajaProfesor/{id}")
	public String BajaProfesor(@PathVariable(value="id")int matricula,Model model) {

		Profesor profesors=profesorrepository.findById(matricula).get();
		profesors.setCurso(null);
		profesorrepository.save(profesors);
		model.addAttribute("Cursos",cursorepository.findAll());
		return "IndexCurso";
	}

	@GetMapping("EliminaCurso/{id}")
	public String BorraCurso(@PathVariable(value="id")int RFC,Model model) {
		Calificacion calificacion;
		Profesor profesor;
		curso=cursorepository.findById(RFC).get();
		Iterable<CursoAlumno> Coleccion=cursoAlumnoRepository.findAll();
		Iterator<CursoAlumno> Coleccion1=Coleccion.iterator();
		while(Coleccion1.hasNext()) {
			CursoAlumno cursosAlumno=Coleccion1.next();
			if(cursosAlumno.getId().getRFC()==curso.getRFC()) {
				calificacion=calificacionrepository.findById(cursosAlumno.getCalificacion().getIdCalifica()).get();
				cursoAlumnoRepository.delete(cursosAlumno);
				calificacionrepository.delete(calificacion);
				Coleccion1.remove();
				}
			}			
			
			Iterator<Profesor> Profecoleccion=curso.getProfesor().iterator();
			while(Profecoleccion.hasNext()) {
				profesor=Profecoleccion.next();
				profesorrepository.findById(profesor.getMatriculaProcesor()).get().setCurso(null);
			}
				
			cursorepository.delete(curso);
			model.addAttribute("Cursos",cursorepository.findAll());
			return "IndexCurso";
	}
	
	
}

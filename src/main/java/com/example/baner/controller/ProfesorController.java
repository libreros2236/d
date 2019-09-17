package com.example.baner.controller;

import java.util.Iterator;

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
import com.example.baner.model.Curso;
import com.example.baner.model.CursoAlumno;
import com.example.baner.model.Profesor;
import com.example.baner.repository.CursoAlumnoRepository;
import com.example.baner.repository.CursoRepository;
import com.example.baner.repository.ProfesorRepository;

@Controller
@RequestMapping("/Profesores/")
public class ProfesorController {
	
	@Autowired
	private ProfesorRepository profesorrepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	private Profesor profesor;
	@GetMapping("InicioProfesor")
	public String InicioProfesor(Model model) {
		model.addAttribute("Profesors",profesorrepository.findAll());
		return "IndexProfesor";
	}
	
	@GetMapping("AgregaProfesor")
    public String AgregaProfesor() {
        return "AgregaProfesor";
    }
	
	@PostMapping("AgregaProfesors") 
	public String crearProfesor(@Valid Profesor profesors,Model model) {
		profesorrepository.save(profesors);
		return "redirect:InicioProfesor";	
	}
	
	@GetMapping("EditaProfesor/{id}")
	public String EditaProfesor(@PathVariable(value="id") int matricula,Model model){
			profesor=profesorrepository.findById(matricula).orElseThrow(()->new IllegalArgumentException("Invalid student Id:" + matricula));
			model.addAttribute("ProfesorActu",profesor);
			return "ActualizaProfesor";
	}
	
	@PostMapping("ActualizaProfesor/{id}")
	public String ActualizaProfesor(@PathVariable(value="id") int matricula,@Valid Profesor profesors,Model model){
			profesor=profesorrepository.findById(matricula).get();
			profesor.setNombre(profesors.getNombre());
			profesorrepository.save(profesor);
			model.addAttribute("Profesors",profesorrepository.findAll());
			return "IndexProfesor";	
	}
		
	@GetMapping("EliminaProfesor/{id}")
	public String BorraProfesor(@PathVariable(value="id")int matricula,Model model) {
		Curso curso;
		Iterator<Profesor> pro;
		Profesor prof;
		profesor=profesorrepository.findById(matricula).get();
		
		Iterable<Curso> Coleccion=cursoRepository.findAll();
		Iterator<Curso> Coleccion1=Coleccion.iterator();
		
		while(Coleccion1.hasNext()) {
			curso=Coleccion1.next();
			pro=curso.getProfesor().iterator();
			while(pro.hasNext()) {
				prof=pro.next();
				if(prof.getMatriculaProcesor()==matricula) {
					pro.remove();
				}
			}
		}
		
		profesorrepository.delete(profesor);
		model.addAttribute("Profesors",profesorrepository.findAll());
		return "IndexProfesor";
	}
	
	@PostMapping("/InscribirProfesor")
	public Profesor crearAlumno(@Valid @RequestBody Profesor profesor) {
		return profesorrepository.save(profesor);		
	}
	
	@DeleteMapping("/BajaProfesor/{id}")
	public void BorraProfesors(@PathVariable(value="id")int matricula) {
		Curso curso;
		Iterator<Profesor> pro;
		Profesor prof;
		profesor=profesorrepository.findById(matricula).get();
		
		Iterable<Curso> Coleccion=cursoRepository.findAll();
		Iterator<Curso> Coleccion1=Coleccion.iterator();
		
		while(Coleccion1.hasNext()) {
			curso=Coleccion1.next();
			pro=curso.getProfesor().iterator();
			while(pro.hasNext()) {
				prof=pro.next();
				if(prof.getMatriculaProcesor()==matricula) {
					pro.remove();
				}
			}
		}
		
	
		profesorrepository.delete(profesor);		
	}
	
	@GetMapping("/Profesores/{id}")
	public Profesor ObtenAlumnoMat(@PathVariable(value="id") int matriculaProcesor){
		try {
			profesor=profesorrepository.findById(matriculaProcesor).get();
			return profesor;
		}catch(Exception e) {			
		}
		return null;
	}
	
	@PutMapping("/EditaProfe/{id}")
	public void EditaProfe(@PathVariable(value="id") int matricula,@Valid @RequestBody Profesor profesores){
		profesor=profesorrepository.findById(matricula).get();
		profesor.setNombre(profesores.getNombre());
		profesorrepository.save(profesor);
	}

}

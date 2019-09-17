package com.example.baner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.baner.model.Alumno;
import com.example.baner.model.Calificacion;
import com.example.baner.model.Curso;
import com.example.baner.model.CursoAlumno;
import com.example.baner.model.Profesor;
import com.example.baner.repository.AlumnoRepository;
import com.example.baner.repository.CalificacionRepository;
import com.example.baner.repository.CursoAlumnoRepository;
import com.example.baner.repository.CursoRepository;

@RestController
public class CursoAlumnoController {
	
	@Autowired
	private CursoAlumnoRepository cursoAlumnoRepository;
	
	@Autowired
	private CalificacionRepository calificacionrepository;
	
	@Autowired
	private AlumnoRepository alumnorepository;
	
	@Autowired
	private CursoRepository cursorepository;
	
	Alumno alumno;
	Curso curso;
	
	@PostMapping("/InscribeAlumnoClase/{idC}/{idA}")
	public void InscribeAlumnoClase(@PathVariable(value="idC")int RFC,@PathVariable(value="idA")int matricula){
			Calificacion calificacion=new Calificacion();
			alumno=alumnorepository.findById(matricula).get();
			curso=cursorepository.findById(RFC).get();
			CursoAlumno AlumnoAltaCurso=new CursoAlumno(alumno,curso,calificacion);
			calificacionrepository.save(calificacion);
			cursoAlumnoRepository.save(AlumnoAltaCurso);
	}

}

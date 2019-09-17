package com.example.baner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.baner.model.Alumno;
import com.example.baner.model.Curso;
import com.example.baner.model.Expediente;
import com.example.baner.repository.AlumnoRepository;
import com.example.baner.repository.ExpedienteRepository;

@RestController
public class ExpedienteController {
	
	@Autowired
	private ExpedienteRepository expedienterepository;
	
	@Autowired
	private AlumnoRepository alumnorepository;
	

}

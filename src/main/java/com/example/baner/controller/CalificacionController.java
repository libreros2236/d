package com.example.baner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.baner.repository.CalificacionRepository;

@RestController
public class CalificacionController {
	
	@Autowired
	private CalificacionRepository calificacionrepository;

}

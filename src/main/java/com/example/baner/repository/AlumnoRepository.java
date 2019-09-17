package com.example.baner.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno,Integer>{}

package com.example.baner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Alumno;
import com.example.baner.model.Curso;
import com.example.baner.model.CursoAlumno;

@Repository
public interface CursoAlumnoRepository extends CrudRepository<CursoAlumno,Integer>{

}

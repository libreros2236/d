package com.example.baner.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Integer>{
	
	

}

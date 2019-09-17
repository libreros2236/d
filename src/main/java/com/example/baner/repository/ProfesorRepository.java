package com.example.baner.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Integer>{

}

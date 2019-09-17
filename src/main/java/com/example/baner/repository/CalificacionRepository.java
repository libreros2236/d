package com.example.baner.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Calificacion;

@Repository
public interface CalificacionRepository extends CrudRepository<Calificacion,Integer>{

}

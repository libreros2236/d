package com.example.baner.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.baner.model.Expediente;

@Repository
public interface ExpedienteRepository extends CrudRepository<Expediente,Integer> {

}

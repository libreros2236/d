package com.example.baner.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Alumnos")
public class Alumno {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int matricula;
	
	@Column(name="nombre")
	private String nombre;
   
	@OneToMany(mappedBy="alumno")
	private Set<CursoAlumno> MateriasInscritas = new HashSet<CursoAlumno>();


	@OneToOne
	@JoinColumn(name="id_expediente")
	private Expediente expediente;
	
	public Alumno() {
	}
	
	public Alumno(String nombre) {
		this.nombre=nombre;
	}
	
	public Alumno(String nombre,Expediente expediente) {
		this.nombre=nombre;
		this.expediente=expediente;
	}

	public Set<CursoAlumno> getMateriasInscritas() {
		return MateriasInscritas;
	}


	public void setMateriasInscritas(Set<CursoAlumno> materiasInscritas) {
		MateriasInscritas = materiasInscritas;
	}


	public Expediente getExpediente() {
		return expediente;
	}


	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}

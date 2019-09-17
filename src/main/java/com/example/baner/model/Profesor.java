package com.example.baner.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Profesor")
public class Profesor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int matriculaProcesor;
	
	@Column(name="nombre",length=50)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;
	
	public int getMatriculaProcesor() {
		return matriculaProcesor;
	}

	public void setMatriculaProcesor(int matriculaProcesor) {
		this.matriculaProcesor = matriculaProcesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
	
}

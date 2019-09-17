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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true)
	private int RFC;
	
	@Column(name="nombre")
	private String nombre;

	@OneToMany(mappedBy="curso")
	private Set<Profesor> profesor;
	
	@OneToMany(mappedBy="curso")
	private Set<CursoAlumno> ListAlumno=new HashSet<CursoAlumno>();


	public Set<CursoAlumno> getListAlumno() {
		return ListAlumno;
	}


	public void setListAlumno(Set<CursoAlumno> listAlumno) {
		ListAlumno = listAlumno;
	}


	public int getRFC() {
		return RFC;
	}

	public void setRFC(int rFC) {
		RFC = rFC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Set<Profesor> getProfesor() {
		return profesor;
	}


	public void setProfesor(Set<Profesor> profesor) {
		this.profesor = profesor;
	}
	
}

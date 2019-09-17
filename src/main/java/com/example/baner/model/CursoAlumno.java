package com.example.baner.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cursos_Alumno")
public class CursoAlumno {
	
	@EmbeddedId
    private Curso_Alumno id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("matricula")
	private Alumno alumno;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("RFC")
	private Curso curso;
	
	@OneToOne
	@JoinColumn(name="Id_calificacion")
	private Calificacion calificacion;
	
	
	private CursoAlumno() {}
	
	public CursoAlumno(Alumno alumno,Curso curso,Calificacion calificacion) {
		this.id=new Curso_Alumno(alumno.getMatricula(),curso.getRFC());
		this.alumno=alumno;
		this.curso=curso;
		this.calificacion=calificacion;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CursoAlumno that = (CursoAlumno) o;
        return Objects.equals(alumno, that.alumno) &&
        		Objects.equals(curso, that.curso);
    }
 

	public Curso_Alumno getId() {
		return id;
	}


	public void setId(Curso_Alumno id) {
		this.id = id;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public Calificacion getCalificacion() {
		return calificacion;
	}


	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}


	@Override
    public int hashCode() {
        return Objects.hash(alumno, curso);
    }
	 

}

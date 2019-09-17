package com.example.baner.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Curso_Alumno implements Serializable{
	
	@Column(name = "fk_alumno")
	private int matricula;
	
	@Column(name = "fk_curso")
	private int RFC;
	
	private Curso_Alumno() {}
	
	public Curso_Alumno(int matricula,int RFC) {
		this.matricula=matricula;
		this.RFC=RFC;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        Curso_Alumno that = (Curso_Alumno) o;
	        return Objects.equals(matricula, that.matricula) &&
	               Objects.equals(RFC, that.RFC);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(matricula, RFC);
	    }

		public int getMatricula() {
			return matricula;
		}

		public void setMatricula(int matricula) {
			this.matricula = matricula;
		}

		public int getRFC() {
			return RFC;
		}

		public void setRFC(int rFC) {
			RFC = rFC;
		}

}

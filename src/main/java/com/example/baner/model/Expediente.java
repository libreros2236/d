package com.example.baner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Expediente")
public class Expediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idexpediente;
	
	@Column(name="descripcion",length=100)
	private String descripcion;

	public int getIdexpediente() {
		return idexpediente;
	}

	public void setIdexpediente(int idexpediente) {
		this.idexpediente = idexpediente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}

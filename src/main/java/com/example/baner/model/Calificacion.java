package com.example.baner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Calificacion")
public class Calificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCalifica;
	
	public int getIdCalifica() {
		return idCalifica;
	}	
	
	public void setIdCalifica(int idCalifica) {
		this.idCalifica = idCalifica;
	}

	public float getCalifi() {
		return califi;
	}

	public void setCalifi(float califi) {
		this.califi = califi;
	}

	@Column(name="Calificacion")
	private float califi;
	

}

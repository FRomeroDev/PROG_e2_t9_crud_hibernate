package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* 1 CREACIÓN DE MODELO HIBERNATE PARA ASOCIARLO A LA BD */

@Entity
@Table(name = "ciudad")
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private int codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "numHabi")
	private int numHabi;

	public Ciudad() {
		super();
	}

	public Ciudad(String nombre, int numHbit) {
		super();
		this.nombre = nombre;
		this.numHabi = numHbit;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumHabi() {
		return numHabi;
	}

	public void setNumHabi(int numHabi) {
		this.numHabi = numHabi;
	}



}

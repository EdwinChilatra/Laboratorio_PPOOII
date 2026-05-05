package com.Proyecto.PPOOII.Entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "persona", schema = "PPOOII")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	public int id;
	
	@Column(name="PNOMBRE")
	private String pnombre;
	
	@Column(name="EDAD")
	private int edad;
	
	@Column(name="ubicacion")
	private String ubicacion;
	
	public Persona () {}
	
	public Persona(String pnombre, int Edad, String ubicacion) {
		super();
		this.pnombre = pnombre;
		this.edad = Edad;
		this.ubicacion = ubicacion;
	}
	
	public Persona(int Id, String pnombre, int Edad, String ubicacion) {
		super();
		this.id = Id;
		this.pnombre = pnombre;
		this.edad = Edad;
		this.ubicacion = ubicacion;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPnombre() {
		return pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Persona [id=" + this.id + ", Primer Nombre=" + this.pnombre + ", Edad=" + this.edad + ", Ubicacion: " + this.ubicacion +"]" ;
	}
}
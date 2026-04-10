package com.Proyecto.PPOOII.Interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Proyecto.PPOOII.Entities.Persona;


public interface IPersonaService {
	
	//METODOS CRUD
	public boolean guardar(Persona persona);
	
	public boolean actualizar(Persona persona);
	
	public boolean eliminar (int id);
	
	public List<Persona> consultarPersona(Pageable pageable);
	
	//LISTA DE PERSONA POR ID
	public Persona findById(int id);
	
	//LISTA DE PERSONA POR PRIMER NOMBRE
	public List<Persona> findByNombre(String pnombre);
	
	//LISTA DE PERSONA POR EDAD
	public List<Persona> findByEdad(int edad);
	

}

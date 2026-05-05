package com.Proyecto.PPOOII.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.Proyecto.PPOOII.Entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>, CrudRepository<Persona, Integer> {

   	
	//Hay Métodos que JPA ya los tiene desarrollados, se pueden crear para tener
	//una manipulación más especifica a la hora de usarlos en el service	

	public abstract Persona findById(int id);

	public abstract List<Persona> findByPnombre(String pnombre);

	public abstract List<Persona> findByEdad(int edad);

	public abstract Page<Persona> findAll(Pageable pageable);
}
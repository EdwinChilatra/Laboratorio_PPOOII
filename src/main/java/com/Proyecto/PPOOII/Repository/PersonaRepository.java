package com.Proyecto.PPOOII.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Proyecto.PPOOII.Entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    // 🔥 IMPORTANTE: debe coincidir con "pnombre"
    List<Persona> findByPnombre(String pnombre);

    List<Persona> findByEdad(int edad);

    Page<Persona> findAll(Pageable pageable);
}
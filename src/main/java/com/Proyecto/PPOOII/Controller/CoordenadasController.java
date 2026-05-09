package com.Proyecto.PPOOII.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.data.domain.Pageable;


import com.Proyecto.PPOOII.Entities.Coordenadas;
import com.Proyecto.PPOOII.Services.CoordenadasServiceImpl;


@RestController
@RequestMapping("/LaboratorioV1")
public class CoordenadasController {

    // ==========INYECCION DEL SERVICE==========
	@Autowired
	private CoordenadasServiceImpl coordenadaService;
	
	// ==========MÉTODOS HTTP====================
	// MÉTODO GET
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/coordenadas")
	public List<Coordenadas> consultarAllCoordenadas(Pageable pageable) {
		return coordenadaService.consultarAllCoordenadas(pageable);
	}
}

package com.Proyecto.PPOOII.Services;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Proyecto.PPOOII.Entities.Coordenadas;
import com.Proyecto.PPOOII.Interfaces.ICoordenadasService;
import com.Proyecto.PPOOII.Repository.CoordenadasRepository;

@Service("CoordenadasService")
public class CoordenadasServiceImpl implements ICoordenadasService{

    // ========= INYECCIÓN DE DEPENDENCIAS ==========
	@Autowired

	private CoordenadasRepository ICoordenadaRepository;
	//==================== LOGS ============================
	//LOGS DE ERROR
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(PersonaServiceImpl.class);
		
	@Override
	public List<Coordenadas> consultarAllCoordenadas(Pageable pageable) {
		return  ICoordenadaRepository.findAll(pageable).getContent();
	}
}

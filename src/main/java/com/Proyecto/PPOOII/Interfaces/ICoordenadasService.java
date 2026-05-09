package com.Proyecto.PPOOII.Interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.Proyecto.PPOOII.Entities.Coordenadas;

public interface ICoordenadasService {

    
	List<Coordenadas> consultarAllCoordenadas(Pageable pageable);
}

package com.Proyecto.PPOOII.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Proyecto.PPOOII.APIs.GoogleMaps.Geocoder;
import com.Proyecto.PPOOII.Entities.Coordenadas;
import com.Proyecto.PPOOII.Entities.Persona;
import com.Proyecto.PPOOII.Repository.CoordenadasRepository;
import com.Proyecto.PPOOII.Repository.PersonaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    // ========= INYECCIÓN DE DEPENDENCIAS ==========
 	@Autowired
 	private PersonaRepository IPersonaRepository;
 	
 	@Autowired

	private CoordenadasRepository ICoordenadaRepository;
 	
    /*
    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
        logger.info("*************Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("++++++++++++Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("-----------Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
	*/
    
    @Scheduled(cron = "*/30 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try
        {
        	List<Persona> listPersonas = IPersonaRepository.getPersonas();
        	if(listPersonas !=null) {
        		if(listPersonas.size()>0) {
        			Geocoder geocoder = new Geocoder();
        			Coordenadas coorXper = new Coordenadas();
        			for (Persona persona : listPersonas) {
        				String LatLng = geocoder.getLatLng(persona.getUbicacion());
        				String[] coor = LatLng.split(",");
                    	logger.info(LatLng + " - {}", dateTimeFormatter.format(LocalDateTime.now()));
                    	
                    	coorXper = ICoordenadaRepository.getCoordenadaXPersona(persona.id);
                    	if(coorXper == null) {
                    		ICoordenadaRepository.save(new Coordenadas(persona.id, persona.getPnombre(), 
									Double.parseDouble(coor[0].toString()),
									Double.parseDouble(coor[1].toString())));
                    	}else if(coorXper.id>0) {
                    		ICoordenadaRepository.save(new Coordenadas(coorXper.id,persona.id, persona.getPnombre(), 
									Double.parseDouble(coor[0].toString()),
									Double.parseDouble(coor[1].toString())));
                    	}
                    	
					}
        		}
        	}
        }catch (Exception e) {
        	System.out.println(e.getMessage());
		}
    }

     // ========= CADA 2 MINUTOS ==========
     @Scheduled(cron = "0 */2 * * * ?")
     public void cadaDosMinutos() {
 
         logger.info("Cada 2 minutos :: {}",
                 dateTimeFormatter.format(LocalDateTime.now()));
     }
 
     // ========= TODOS LOS LUNES A LAS 5:17 PM ==========
     @Scheduled(cron = "0 17 17 ? * MON")
     public void lunes517PM() {
 
         logger.info("Lunes 5:17 PM :: {}",
                 dateTimeFormatter.format(LocalDateTime.now()));
     }
 
     // ========= TRES VECES AL DÍA: LUNES, MIÉRCOLES Y VIERNES ==========
     @Scheduled(cron = "0 0 8,12,18 ? * MON,WED,FRI")
     public void tresVecesDia() {
 
         logger.info("Lunes, miércoles y viernes :: {}",
                 dateTimeFormatter.format(LocalDateTime.now()));
     }
}

package com.dh.Odontologia;

import com.dh.Odontologia.DAOs.Impl.DomicilioDaoImpl;
import com.dh.Odontologia.DAOs.Impl.OdontologoDaoImpl;
import com.dh.Odontologia.DAOs.Impl.PacienteDaoImpl;
import com.dh.Odontologia.DAOs.Impl.TurnoDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OdontologiaApplication {
	private final static Logger LOGGER = Logger.getLogger(OdontologiaApplication.class);

	public static void main(String[] args) {

		//creando tabla odontologo
		OdontologoDaoImpl odontologoDao = new OdontologoDaoImpl();

		try{
			odontologoDao.crearTablas();
		}catch (Exception e){
			LOGGER.error("No se pudo crear las tablas", e);
			return;
		}

		//creando tabla domicilios
		DomicilioDaoImpl domicilioDao = new DomicilioDaoImpl();

		try{
			domicilioDao.crearTablas();
		}catch (Exception e){
			LOGGER.error("No se pudo crear las tablas", e);
			return;
		}

		//creando tabla pacientes
		PacienteDaoImpl pacienteDao = new PacienteDaoImpl();

		try{
			pacienteDao.crearTablas();
		}catch (Exception e){
			LOGGER.error("No se pudo crear las tablas", e);
			return;
		}

		//creando tabla Turnos
		TurnoDaoImpl turnoDao = new TurnoDaoImpl();

		try{
			turnoDao.crearTablas();
		}catch (Exception e){
			LOGGER.error("No se pudo crear las tablas", e);
			return;
		}


		SpringApplication.run(OdontologiaApplication.class, args);



	}

}

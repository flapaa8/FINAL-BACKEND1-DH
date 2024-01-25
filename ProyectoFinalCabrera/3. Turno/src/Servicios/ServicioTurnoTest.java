package Servicios;

import DAOs.Impl.TurnoDAOImpl;
import Entidades.Turno;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ServicioTurnoTest {
    private Logger LOGGER = Logger.getLogger(ServicioTurnoTest.class);

    @Test
    void asignarCita(){

        TurnoDAOImpl turnoDAO = new TurnoDAOImpl();
        try{
            turnoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }

        ServicioTurno servicioTurno = new ServicioTurno(turnoDAO);

        LocalDateTime fechahoraturno1 = LocalDateTime.of(2023,8,17,15,30,0);
        java.sql.Timestamp fecha1 = java.sql.Timestamp.valueOf(fechahoraturno1);
        Turno turno1 = new Turno(1, 2, 3, fecha1 );


        assertEquals(true, servicioTurno.asigarCita(turno1));
    }

    @Test
    void listarOdontologos(){
        TurnoDAOImpl turnoDAO = new TurnoDAOImpl();
        try{
            turnoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }

        ServicioTurno servicioTurno = new ServicioTurno(turnoDAO);

        LocalDateTime fechahoraturno1 = LocalDateTime.of(2023,8,17,15,30,0);
        java.sql.Timestamp fecha1 = java.sql.Timestamp.valueOf(fechahoraturno1);
        Turno turno1 = new Turno(1, 2, 3, fecha1 );
        servicioTurno.asigarCita(turno1);

        assertEquals("Listado", servicioTurno.listarTurnos());

    }

    @Test
    void modificarOdontologo(){
        TurnoDAOImpl turnoDAO = new TurnoDAOImpl();
        try{
            turnoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }

        ServicioTurno servicioTurno = new ServicioTurno(turnoDAO);

        LocalDateTime fechahoraturno1 = LocalDateTime.of(2023,8,17,15,30,0);
        java.sql.Timestamp fecha1 = java.sql.Timestamp.valueOf(fechahoraturno1);
        Turno turno1 = new Turno(1, 2, 3, fecha1 );
        servicioTurno.asigarCita(turno1);
        Turno turnoModificar = new Turno(1, 6,6, fecha1);

        assertEquals(true, servicioTurno.modificarTurno(1, turnoModificar));

    }

    @Test
    void eliminarOdontologo(){
        TurnoDAOImpl turnoDAO = new TurnoDAOImpl();
        try{
            turnoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }

        ServicioTurno servicioTurno = new ServicioTurno(turnoDAO);

        LocalDateTime fechahoraturno1 = LocalDateTime.of(2023,8,17,15,30,0);
        java.sql.Timestamp fecha1 = java.sql.Timestamp.valueOf(fechahoraturno1);
        Turno turno1 = new Turno(1, 2, 3, fecha1 );
        servicioTurno.asigarCita(turno1);
        assertEquals(true, servicioTurno.eliminarTurno(1));
    }
}
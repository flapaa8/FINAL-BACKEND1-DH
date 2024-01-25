import DAOs.Impl.TurnoDAOImpl;
import Entidades.Turno;
import Servicios.ServicioTurno;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        //creando tabla turnos
        TurnoDAOImpl turnoDAO = new TurnoDAOImpl();
        try{
            turnoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }

        ServicioTurno servicioTurno = new ServicioTurno(turnoDAO);

        //creando turnos para prueba

        LocalDateTime fechahoraturno1 = LocalDateTime.of(2023,8,17,15,30,0);
        LocalDateTime fechahoraturno2 = LocalDateTime.of(2023,12,30,13,00,0);
        LocalDateTime fechahoraturno3 = LocalDateTime.of(2023,11,21,11,30,0);

        java.sql.Timestamp fecha1 = java.sql.Timestamp.valueOf(fechahoraturno1);
        java.sql.Timestamp fecha2 = java.sql.Timestamp.valueOf(fechahoraturno2);
        java.sql.Timestamp fecha3 = java.sql.Timestamp.valueOf(fechahoraturno3);

        Turno turno1 = new Turno(1, 2, 3, fecha1 );
        Turno turno2 = new Turno(2 ,5, 2, fecha2 );
        Turno turno3 = new Turno(3, 7, 6, fecha3 );
        Turno turnoModificar = new Turno(1, 6,6, fecha3);

        //registrar los turnos en la tabla
        servicioTurno.asigarCita(turno1);
        servicioTurno.asigarCita(turno2);
        servicioTurno.asigarCita(turno3);

        //listando los turnos ingresados en la tabla
        servicioTurno.listarTurnos();

        //modificando turnos
        servicioTurno.modificarTurno(1, turnoModificar);

        //eliminando turnos
        servicioTurno.eliminarTurno(2);

        //buscar turno
        Turno turno5 = new Turno();
        turno5 = servicioTurno.buscarTurno(1);
        LOGGER.info("se encontro el turno: " + turno5);

    }
}
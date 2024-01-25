package Servicios;

import DAOs.TurnoDAO;
import Entidades.Turno;
import org.apache.log4j.Logger;

public class ServicioTurno {
    private TurnoDAO turnoDAO;
    private Logger LOGGER = Logger.getLogger(ServicioTurno.class);

    public ServicioTurno() {
    }

    public ServicioTurno(TurnoDAO turnoDAO) {
        this.turnoDAO = turnoDAO;
    }

    public boolean asigarCita(Turno turno){

        //ejecutamos el metodo
        try{
            turnoDAO.asignarCita(turno);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }

        return true;

    }

    public String listarTurnos(){
        try{
            turnoDAO.listarTurnos();
        }catch (Exception e){
            return "No listado";
        }
        return "Listado";
    }

    public boolean modificarTurno(int id, Turno turno){
        try{
            turnoDAO.modificarTurno(id, turno);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }

        return true;
    }

    public boolean eliminarTurno(int id){
        try{
            turnoDAO.eliminarTurno(id);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }
        return true;
    }


    public Turno buscarTurno(int id){
        Turno turno = null;
        try{
            turno = turnoDAO.buscarTurno(id);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);

        }
        return turno;
    }
}

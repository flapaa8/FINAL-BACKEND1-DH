package Servicios;

import DAOs.Impl.PacienteDAOImpl;
import DAOs.PacienteDAO;
import Entidades.Paciente;
import org.apache.log4j.Logger;

import java.sql.Date;

public class ServicioPaciente {

    private PacienteDAO pacienteDAO;
    private Logger LOGGER = Logger.getLogger(ServicioPaciente.class);

    public ServicioPaciente() {
    }

    public ServicioPaciente(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public boolean registrarPaciente(Paciente paciente){

        try{
            pacienteDAO.registrarPaciente(paciente);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }

        return true;

    }

    public String listarPacientes(){
        try{
            pacienteDAO.listarPacientes();
        }catch (Exception e){
            return "No listado";
        }
        return "Listado";
    }

    public boolean modificarPaciente(int id, Paciente paciente){
        try{
            pacienteDAO.modificarPaciente(id,paciente);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }

        return true;
    }

    public boolean eliminarPaciente(int id){
        try{
            pacienteDAO.eliminarPaciente(id);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);
            return false;
        }
        return true;
    }

    public Paciente buscarPaciente(int id){
        Paciente paciente = null;
        try{
            paciente = pacienteDAO.buscarPaciente(id);
        }catch (Exception e){
            LOGGER.error("Hay un error", e);

        }
        return paciente;
    }
}

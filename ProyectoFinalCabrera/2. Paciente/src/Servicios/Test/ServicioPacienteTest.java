package Servicios.Test;
import DAOs.Impl.PacienteDAOImpl;
import Entidades.Paciente;
import Servicios.ServicioPaciente;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServicioPacienteTest {
private Logger LOGGER = Logger.getLogger(ServicioPacienteTest.class);
    @Test
    void registrarPaciente() {
        PacienteDAOImpl pacienteDAO = new PacienteDAOImpl();
        try{
            pacienteDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteDAO);

        String fechaPaciente1 = "2023-01-02";
        Date fecha1 = Date.valueOf(fechaPaciente1);
        Paciente paciente1 = new Paciente(1, "Pedro", "Cortez", "carrera 25", fecha1 );

        assertEquals(true, servicioPaciente.registrarPaciente(paciente1));
    }

    @Test
    void listarPacientes() {
        PacienteDAOImpl pacienteDAO = new PacienteDAOImpl();
        try{
            pacienteDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteDAO);

        String fechaPaciente1 = "2023-01-02";
        Date fecha1 = Date.valueOf(fechaPaciente1);
        Paciente paciente1 = new Paciente(1, "Pedro", "Cortez", "carrera 25", fecha1 );
        servicioPaciente.registrarPaciente(paciente1);

        assertEquals("Listado", servicioPaciente.listarPacientes());
    }

    @Test
    void modificarPaciente() {
        PacienteDAOImpl pacienteDAO = new PacienteDAOImpl();
        try{
            pacienteDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteDAO);

        String fechaPaciente1 = "2023-01-02";
        Date fecha1 = Date.valueOf(fechaPaciente1);
        Paciente paciente1 = new Paciente(1, "Pedro", "Cortez", "carrera 25", fecha1 );
        servicioPaciente.registrarPaciente(paciente1);
        Paciente pacienteModificar = new Paciente(5, "Lauren", "Madrigal", "carrera 112", fecha1 );

        assertEquals(true, servicioPaciente.modificarPaciente(1, pacienteModificar));

    }

    @Test
    void eliminarPaciente() {
        PacienteDAOImpl pacienteDAO = new PacienteDAOImpl();
        try{
            pacienteDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteDAO);

        String fechaPaciente1 = "2023-01-02";
        Date fecha1 = Date.valueOf(fechaPaciente1);
        Paciente paciente1 = new Paciente(1, "Pedro", "Cortez", "carrera 25", fecha1 );
        servicioPaciente.registrarPaciente(paciente1);

        assertEquals(true, servicioPaciente.eliminarPaciente(1));
    }
}
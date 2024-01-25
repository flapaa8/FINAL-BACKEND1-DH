import DAOs.Impl.PacienteDAOImpl;
import Entidades.Paciente;
import Servicios.ServicioPaciente;
import org.apache.log4j.Logger;

import java.sql.Date;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        //creando tabla pacientes

        PacienteDAOImpl pacienteDAO = new PacienteDAOImpl();
        try{
            pacienteDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteDAO);

        //creando fechas para prueba
        String fechaPaciente1 = "2023-01-02";
        String fechaPaciente2 = "2023-06-06";
        String fechaPaciente3 = "2023-07-10";
        String fechaPaciente4 = "2023-08-16";
        Date fecha1 = Date.valueOf(fechaPaciente1);
        Date fecha2 = Date.valueOf(fechaPaciente2);
        Date fecha3 = Date.valueOf(fechaPaciente3);
        Date fecha4 = Date.valueOf(fechaPaciente4);

        //Creando pacientes para prueba
        Paciente paciente1 = new Paciente(1, "Pedro", "Cortez", "carrera 25", fecha1 );
        Paciente paciente2 = new Paciente(2, "Juan", "Lorenz", "carrera 81", fecha2 );
        Paciente paciente3 = new Paciente(3, "Fabio", "Mendoza", "carrera 52", fecha3 );
        Paciente paciente4 = new Paciente(4, "Enrique", "Manrique", "carrera 01", fecha4 );
        Paciente pacienteModificar = new Paciente(5, "Lauren", "Madrigal", "carrera 112", fecha1 );

        //Registrando pacientes en la tabla
        servicioPaciente.registrarPaciente(paciente1);
        servicioPaciente.registrarPaciente(paciente2);
        servicioPaciente.registrarPaciente(paciente3);
        servicioPaciente.registrarPaciente(paciente4);

        //listando los pacientes ingresados a la tabla
        servicioPaciente.listarPacientes();

        //modificando paciente1
        servicioPaciente.modificarPaciente(1, pacienteModificar);
        servicioPaciente.listarPacientes();

        //eliminando paciente2
        servicioPaciente.eliminarPaciente(2);
        servicioPaciente.listarPacientes();

        //buscar paciente
        Paciente paciente5 = new Paciente();
        paciente5 = servicioPaciente.buscarPaciente(1);
        LOGGER.info("se encontro el paciente: " + paciente5);

    }
}
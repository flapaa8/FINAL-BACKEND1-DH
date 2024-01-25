package Servicios.Test;

import DAOs.Impl.OdontologoDAOImpl;
import Entidades.Odontologo;
import Servicios.ServicioOdontologo;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicioOdontologoTest {

    private Logger LOGGER = Logger.getLogger(ServicioOdontologoTest.class);


    @Test
    void registrarOdontologo(){

        OdontologoDAOImpl odontologoDAO = new OdontologoDAOImpl();
        try{
            odontologoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioOdontologo servicioOdontologo = new ServicioOdontologo(odontologoDAO);

        Odontologo odontologo1 = new Odontologo(1, 1010, "Manuela", "Ortiz");

        assertEquals(true, servicioOdontologo.registrarOdontologo(odontologo1));
    }

    @Test
    void listarOdontologos(){
        OdontologoDAOImpl odontologoDAO = new OdontologoDAOImpl();
        try{
            odontologoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioOdontologo servicioOdontologo = new ServicioOdontologo(odontologoDAO);

        Odontologo odontologo1 = new Odontologo(1, 1010, "Manuela", "Ortiz");
        servicioOdontologo.registrarOdontologo(odontologo1);

        assertEquals("Listado", servicioOdontologo.listarOdontologos());

    }

    @Test
    void modificarOdontologo(){
        OdontologoDAOImpl odontologoDAO = new OdontologoDAOImpl();
        try{
            odontologoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioOdontologo servicioOdontologo = new ServicioOdontologo(odontologoDAO);

        Odontologo odontolo1 = new Odontologo(1, 1010, "Manuela", "Ortiz");
        Odontologo odontologoModificar = new Odontologo(5, 1189, "Lucia", "Meneses");
        servicioOdontologo.registrarOdontologo(odontolo1);

        assertEquals(true,servicioOdontologo.modificarOdontologo(1, odontologoModificar));

    }

    @Test
    void eliminarOdontologo(){
        OdontologoDAOImpl odontologoDAO = new OdontologoDAOImpl();
        try{
            odontologoDAO.creatTablas();
        }catch (Exception e){
            LOGGER.error("No se pudo crear las tablas", e);
            return;
        }
        ServicioOdontologo servicioOdontologo = new ServicioOdontologo(odontologoDAO);

        Odontologo odontolo1 = new Odontologo(1, 1010, "Manuela", "Ortiz");
        servicioOdontologo.registrarOdontologo(odontolo1);
        assertEquals(true, servicioOdontologo.eliminarOdontologo(1));
    }

}
package com.dh.Odontologia.DAOs.Impl;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Domicilio;
import com.dh.Odontologia.Entidades.Paciente;
import com.dh.Odontologia.Utils.SQLQueries;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoImpl implements IDAO<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(PacienteDaoImpl.class);

    private static Connection connection;
    private DomicilioDaoImpl domicilioDao = new DomicilioDaoImpl();

    public static Connection getConnection() {
        return connection;
    }

    public PacienteDaoImpl() {
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:tcp://localhost/~/test";
            connection = DriverManager.getConnection(url, "sa", "");
        } catch (Exception e) {
            LOGGER.error("No se pudo crear la conexión DAO", e);
        }
    }
    public void crearTablas() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute(SQLQueries.CREATETABLES_PACIENTES);
        statement.close();
    }


    @Override
    public Paciente guardar(Paciente paciente) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_CUSTOM_PACIENTES)){
            Domicilio domicilio = domicilioDao.guardar(paciente.getDomicilio());
            paciente.getDomicilio().setId(domicilio.getId());

            statement.setInt(1, paciente.getId());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido());
            statement.setInt(4, paciente.getDomicilio().getId());
            statement.setDate(5, paciente.getFechaAlta());
            statement.execute();
            LOGGER.info("Se creo un paciente" + paciente);

        }catch (Exception e){
            LOGGER.error("No fue posible crear el paciente" + paciente, e);
            throw new Exception("Sucedio un error al crear el paciente");
        }
        return paciente;
    }

    @Override
    public List<Paciente> listar() throws Exception {
        List<Paciente> pacientes = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_PACIENTES)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt(1));
                paciente.setNombre(resultSet.getString(2));
                paciente.setApellido(resultSet.getString(3));
                paciente.setFechaAlta(resultSet.getDate(5));

                int idDomicilio = resultSet.getInt(4);
                Domicilio domicilio = domicilioDao.buscar(idDomicilio);
                paciente.setDomicilio(domicilio);

                pacientes.add(paciente);
            }
        }catch (Exception e) {
            LOGGER.error("No fue posible listar los pacientes", e);
        }
        return pacientes;
    }

    @Override
    public Paciente modificar(Paciente paciente) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_PACIENTE)){

            statement.setInt(5, paciente.getId());
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellido());
            statement.setDate(4, paciente.getFechaAlta());
            statement.setInt(3, paciente.getDomicilio().getId());

            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del paciente");
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar"+ e);
        }
        return paciente;
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.DETELE_CUSTOM_PACIENTE)){
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            LOGGER.info("Se elimino el paciente con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al eliminar el paciente", e);
            throw new Exception("Sucedio un error al eliminar");
        }
    }

    @Override
    public Paciente buscar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_PACIENTE)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1){
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt(1));
                paciente.setNombre(resultSet.getString(2));
                paciente.setApellido(resultSet.getString(3));
                paciente.setFechaAlta(resultSet.getDate(5));

                int idDomicilio = resultSet.getInt(4);
                Domicilio domicilio = domicilioDao.buscar(idDomicilio);
                paciente.setDomicilio(domicilio);



                return paciente;
            }else throw new Exception("Error al buscar el paciente");
        }catch (Exception e){
            LOGGER.error("No fue posible encontrar el paciente con id: "+id);
            throw new Exception("sucedio un error al buscar el paciente");

        }
    }
}

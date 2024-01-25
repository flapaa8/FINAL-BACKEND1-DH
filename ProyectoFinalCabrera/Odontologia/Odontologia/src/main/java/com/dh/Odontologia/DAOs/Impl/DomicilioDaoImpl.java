package com.dh.Odontologia.DAOs.Impl;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Domicilio;
import com.dh.Odontologia.Utils.SQLQueries;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoImpl implements IDAO<Domicilio>{
    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoImpl.class);

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public DomicilioDaoImpl() {
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
        statement.execute(SQLQueries.CREATETABLES_DOMICILIO);
        statement.close();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_CUSTOM_DOMICILIO)){
            statement.setInt(1,domicilio.getId() );
            statement.setString(2, domicilio.getCalle());
            statement.setInt(3, domicilio.getNumero());
            statement.setString(4, domicilio.getCiudad());
            statement.setString(5, domicilio.getDepartamento());
            statement.execute();

            LOGGER.info("Se creo un domicilio");

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                domicilio.setId(keys.getInt(1));

            statement.close();

        }catch (Exception e){
            LOGGER.error("No fue posible crear el domicilio" + domicilio, e);
            throw new Exception("Sucedio un error al crear el domicilio");
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> listar() throws Exception {
        List<Domicilio> domicilios = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_DOMICILIOS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

                Domicilio domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt(1));
                domicilio.setCalle(resultSet.getString(2));
                domicilio.setNumero(resultSet.getInt(3));
                domicilio.setCiudad(resultSet.getString(4));
                domicilio.setDepartamento(resultSet.getString(5));

                domicilios.add(domicilio);
            }

        }catch (Exception e) {
            LOGGER.error("No fue posible listar los domicilios", e);
        }

        return domicilios;
    }


    @Override
    public Domicilio modificar(Domicilio domicilio) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_DOMICILIO)){
            statement.setInt(5, domicilio.getId());
            statement.setString(1,domicilio.getCalle() );
            statement.setInt(2, domicilio.getNumero());
            statement.setString(3, domicilio.getCiudad());
            statement.setString(4, domicilio.getDepartamento());
            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del domicilio");
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar");
        }
        return domicilio;
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.DETELE_CUSTOM_DOMICILIO)){
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            LOGGER.info("Se elimino el domicilio con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al eliminar el domicilio", e);
            throw new Exception("Sucedio un error al eliminar");
        }

    }

    @Override
    public Domicilio buscar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_DOMICILIO)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1){
                Domicilio domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt(1));
                domicilio.setCalle(resultSet.getString(2));
                domicilio.setNumero(resultSet.getInt(3));
                domicilio.setCiudad(resultSet.getString(4));
                domicilio.setDepartamento(resultSet.getString(5));

                return domicilio;
            }else throw new Exception("Error al buscar el domicilio");
        }catch (Exception e){
            LOGGER.error("No fue posible encontrar el domicilio con id: "+id);
            throw new Exception("sucedio un error al buscar el domicilio");

        }
    }
}

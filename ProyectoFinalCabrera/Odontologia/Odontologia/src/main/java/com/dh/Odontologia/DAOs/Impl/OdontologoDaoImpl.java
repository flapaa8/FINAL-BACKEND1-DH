package com.dh.Odontologia.DAOs.Impl;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Utils.SQLQueries;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoImpl implements IDAO<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoImpl.class);

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public OdontologoDaoImpl() {
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
        statement.execute(SQLQueries.CREATETABLES_ODONTOLOGO);
        statement.close();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) throws Exception { //probado
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_CUSTOM_ODONTOLOGO)){
            statement.setInt(1,odontologo.getId() );
            statement.setInt(2, odontologo.getMatricula());
            statement.setString(3, odontologo.getNombre());
            statement.setString(4, odontologo.getApellido());
            statement.execute();

            LOGGER.info("Se creo un odontologo");

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                odontologo.setId(keys.getInt(1));

            statement.close();

        }catch (Exception e){
            LOGGER.error("No fue posible crear el odontologo" + odontologo, e);
            throw new Exception("Sucedio un error al crear el odontologo");
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() throws Exception { //probado
        List<Odontologo> odontologos = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_ODONTOLOGOS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                //LOGGER.info("ID Odontologo " + resultSet.getInt(1) + " Matricula " + resultSet.getInt(2) + " Nombre " + resultSet.getString(3) + " Apellido "  + resultSet.getString(4));
                Odontologo odontologo = new Odontologo();
                odontologo.setId(resultSet.getInt(1));
                odontologo.setMatricula(resultSet.getInt(2));
                odontologo.setNombre(resultSet.getString(3));
                odontologo.setApellido(resultSet.getString(4));

                odontologos.add(odontologo);
            }

        }catch (Exception e) {
            LOGGER.error("No fue posible listar los odontologos", e);
        }

        return odontologos;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_ODONTOLOGO)){
            statement.setInt(4, odontologo.getId());
            statement.setString(1, odontologo.getNombre() );
            statement.setString(2, odontologo.getApellido());
            statement.setInt(3, odontologo.getMatricula());
            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del odontologo con id: ");
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar");
        }
        return odontologo;
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.DETELE_CUSTOM_ODONTOLOGO)){
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            LOGGER.info("Se elimino el odontologo con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al eliminar el odontologo", e);
            throw new Exception("Sucedio un error al eliminar");
        }

    }

    @Override
    public Odontologo buscar(int id) throws Exception {

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_ODONTOLOGO)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1){
                Odontologo odontologo = new Odontologo();
                odontologo.setId(resultSet.getInt(1));
                odontologo.setMatricula(resultSet.getInt(2));
                odontologo.setNombre(resultSet.getString(3));
                odontologo.setApellido(resultSet.getString(4));

                return odontologo;
            }else throw new Exception("Error al buscar el odontologo");
        }catch (Exception e){
            LOGGER.error("No fue posible encontrar el odontologo con id: "+id);
            throw new Exception("sucedio un error al buscar el odontologo");

        }
    }


}

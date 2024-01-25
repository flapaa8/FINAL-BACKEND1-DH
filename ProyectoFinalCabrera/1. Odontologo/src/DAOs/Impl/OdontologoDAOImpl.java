package DAOs.Impl;

import DAOs.OdontologoDAO;
import Entidades.Odontologo;
import Utils.SQLQueriesOdontologo;
import org.apache.log4j.Logger;

import java.sql.*;

public class OdontologoDAOImpl implements OdontologoDAO {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOImpl.class);
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public OdontologoDAOImpl() {
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:tcp://localhost/~/test";
            connection = DriverManager.getConnection(url, "sa", "");
        } catch (Exception e) {
            LOGGER.error("No se pudo crear la conexión DAO", e);
        }
    }

    public void creatTablas() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute(SQLQueriesOdontologo.CREATETABLES);
        statement.close();
    }

    @Override
    public void registrarOdontologo(Odontologo odontologo) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesOdontologo.INSERT_CUSTOM)){
            statement.setInt(1, odontologo.getId());
            statement.setInt(2, odontologo.getMatricula());
            statement.setString(3, odontologo.getNombre());
            statement.setString(4, odontologo.getApellido());
            statement.execute();
            LOGGER.info("Se creo un odontologo" + odontologo);

        }catch (Exception e){
            LOGGER.error("No fue posible crear el odontologo" + odontologo, e);
            throw new Exception("Sucedio un error al crear el odontologo");
        }
    }

    @Override
    public String listarOdontologos() throws Exception {

        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesOdontologo.TRAER_ODONTOLOGOS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                LOGGER.info("ID Odontologo " + resultSet.getInt(1) + " Matricula " + resultSet.getInt(2) + " Nombre " + resultSet.getString(3) + " Apellido "  + resultSet.getString(4));
            }
        }catch (Exception e) {
            LOGGER.error("No fue posible listar los odontologos", e);

            return "No listado";
        }

        return "Listado";
    }

    @Override
    public void modificarOdontologo(int id, Odontologo odontologo ) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesOdontologo.UPDATE_ODONTOLOGO)){
            statement.setInt(4, id);
            statement.setString(1, odontologo.getNombre() );
            statement.setString(2, odontologo.getApellido());
            statement.setInt(3, odontologo.getMatricula());
            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del odontologo con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar");
        }
    }

    @Override
    public void eliminarOdontologo(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesOdontologo.DETELE_CUSTOM)){
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
    public Odontologo buscarOdontologo(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesOdontologo.TRAER_ODONTOLOGO)){
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
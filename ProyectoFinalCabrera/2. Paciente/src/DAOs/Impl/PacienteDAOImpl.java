package DAOs.Impl;

import DAOs.PacienteDAO;
import Entidades.Paciente;
import Utils.SQLQueriesPaciente;
import org.apache.log4j.Logger;

import java.sql.*;

public class PacienteDAOImpl implements PacienteDAO {

    private static final Logger LOGGER = Logger.getLogger(PacienteDAOImpl.class);

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public PacienteDAOImpl() {
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
        statement.execute(SQLQueriesPaciente.CREATETABLES);
        statement.close();
    }

    @Override
    public void registrarPaciente(Paciente paciente) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesPaciente.INSERT_CUSTOM)){
            statement.setInt(1, paciente.getId());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido());
            statement.setString(4, paciente.getDomicilio());
            statement.setDate(5, paciente.getFechaAlta());
            statement.execute();
            LOGGER.info("Se creo un paciente" + paciente);

        }catch (Exception e){
            LOGGER.error("No fue posible crear el paciente" + paciente, e);
            throw new Exception("Sucedio un error al crear el paciente");
        }
    }

    @Override
    public void listarPacientes() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesPaciente.TRAER_PACIENTES)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                LOGGER.info("ID paciente " + resultSet.getInt(1) + " Nombre " + resultSet.getString(2) + " Apellido " + resultSet.getString(3) + " domicilio "  + resultSet.getString(4) + " fecha de alta " + resultSet.getDate(5));
            }
        }catch (Exception e) {
            LOGGER.error("No fue posible listar los pacientes", e);
        }
    }

    @Override
    public void modificarPaciente(int id, Paciente paciente) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesPaciente.UPDATE_PACIENTE)){
            statement.setInt(5, id);
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellido());
            statement.setString(3, paciente.getDomicilio());
            statement.setDate(4, paciente.getFechaAlta());
            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del paciente con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar");
        }
    }

    @Override
    public void eliminarPaciente(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesPaciente.DETELE_CUSTOM)){
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
    public Paciente buscarPaciente(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesPaciente.TRAER_PACIENTE)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1){
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt(1));
                paciente.setNombre(resultSet.getString(2));
                paciente.setApellido(resultSet.getString(3));
                paciente.setDomicilio(resultSet.getString(4));
                paciente.setFechaAlta(resultSet.getDate(5));

                return paciente;
            }else throw new Exception("Error al buscar el paciente");
        }catch (Exception e){
            LOGGER.error("No fue posible encontrar el paciente con id: "+id);
            throw new Exception("sucedio un error al buscar el paciente");

        }
    }
}

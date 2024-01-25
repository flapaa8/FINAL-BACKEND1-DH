package DAOs.Impl;

import DAOs.TurnoDAO;
import Entidades.Turno;
import Utils.SQLQueriesTurno;
import org.apache.log4j.Logger;

import java.sql.*;

public class TurnoDAOImpl implements TurnoDAO {
    private static final Logger LOGGER = Logger.getLogger(TurnoDAOImpl.class);
    private static Connection connection;


    public static Connection getConnection() {
        return connection;
    }

    public TurnoDAOImpl() {
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
        statement.execute(SQLQueriesTurno.CREATETABLES);
        statement.close();
    }

    @Override
    public void asignarCita(Turno turno) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesTurno.INSERT_CUSTOM)){
            statement.setInt(1, turno.getId());
            statement.setInt(2, turno.getIdOdontologo());
            statement.setInt(3, turno.getIdPaciente());
            statement.setTimestamp(4, turno.getFechaHoraTurno());
            statement.execute();
            LOGGER.info("Se creo un Turno" + turno);

        }catch (Exception e){
            LOGGER.error("No fue posible crear el turno " + turno, e);
            throw new Exception("Sucedio un error al crear el turno");
        }


    }

    @Override
    public void listarTurnos() throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesTurno.TRAER_TURNOS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                LOGGER.info("ID Turno " + resultSet.getInt(1) + " ID Odontologo " + resultSet.getInt(2) + " ID Paciente " + resultSet.getInt(3) + " Fecha y hora de la cita "  + resultSet.getTimestamp(4));
            }
        }catch (Exception e) {
            LOGGER.error("No fue posible listar los odontologos", e);

        }
    }

    @Override
    public void modificarTurno(int id, Turno turno) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesTurno.UPDATE_TURNO)){
            statement.setInt(4, id);
            statement.setInt(1, turno.getIdOdontologo());
            statement.setInt(2, turno.getIdPaciente());
            statement.setTimestamp(3, turno.getFechaHoraTurno());
            statement.executeUpdate();
            LOGGER.info("Se moficaron los datos del turno con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al modificar los datos ", e);
            throw new Exception("Sucedio un error al modificar");
        }
    }

    @Override
    public void eliminarTurno(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesTurno.DETELE_CUSTOM)){
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            LOGGER.info("Se elimino el turno con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al eliminar el turno", e);
            throw new Exception("Sucedio un error al eliminar");
        }
    }

    @Override
    public Turno buscarTurno(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueriesTurno.TRAER_TURNO)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1){
                Turno turno = new Turno();
                turno.setId(resultSet.getInt(1));
                turno.setIdOdontologo(resultSet.getInt(2));
                turno.setIdPaciente(resultSet.getInt(3));
                turno.setFechaHoraTurno(resultSet.getTimestamp(4));

                return turno;
            }else throw new Exception("Error al buscar el Turno");
        }catch (Exception e){
            LOGGER.error("No fue posible encontrar el Turno con id: "+id);
            throw new Exception("sucedio un error al buscar el Turno");

        }
    }
}

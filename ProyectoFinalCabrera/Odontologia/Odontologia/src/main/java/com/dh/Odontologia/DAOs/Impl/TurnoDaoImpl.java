package com.dh.Odontologia.DAOs.Impl;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Entidades.Paciente;
import com.dh.Odontologia.Entidades.Turno;
import com.dh.Odontologia.Servicios.ServicioOdontologo;
import com.dh.Odontologia.Utils.SQLQueries;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.thymeleaf.model.IStandaloneElementTag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TurnoDaoImpl implements IDAO<Turno> {

    private Logger LOGGER = Logger.getLogger(TurnoDaoImpl.class);
    public static Connection connection;

    private PacienteDaoImpl pacienteDao = new PacienteDaoImpl();
    private OdontologoDaoImpl odontologoDao = new OdontologoDaoImpl();

    public static Connection getConnection() {
        return connection;
    }

    public TurnoDaoImpl() {
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
        statement.execute(SQLQueries.CREATETABLES_TURNOS);
        statement.close();
    }

    @Override
    public Turno guardar(Turno turno) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_CUSTOM_TURNOS)){
            Paciente paciente = pacienteDao.guardar(turno.getPaciente());
            turno.getPaciente().setId(paciente.getId());
            Odontologo odontologo = odontologoDao.guardar(turno.getOdontologo());
            turno.getOdontologo().setId(odontologo.getId());

            statement.setInt(1, turno.getId());
            statement.setInt(2, turno.getOdontologo().getId());
            statement.setInt(3, turno.getPaciente().getId());
            statement.setDate(4, turno.getFechaTurno());
            statement.execute();
            LOGGER.info("se creo un turno");
        }catch (Exception e){
            LOGGER.error("No fue posible crear el turno", e);
            throw new Exception("sucedio un error creando el turno");
        }

        return turno;
    }

    @Override
    public List<Turno> listar() throws Exception {
        List<Turno> turnos = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_TURNOS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Turno turno = new Turno();
                turno.setId(resultSet.getInt(1));
                turno.setFechaTurno(resultSet.getDate(4));

                int idOdontologo = resultSet.getInt(2);
                Odontologo odontologo = odontologoDao.buscar(idOdontologo);
                turno.setOdontologo(odontologo);

                int idPaciente = resultSet.getInt(3);
                Paciente paciente = pacienteDao.buscar(idPaciente);
                turno.setPaciente(paciente);

                turnos.add(turno);
            }
        }catch (Exception e){
            LOGGER.error("No fue posible listar los turnos", e);
        }
        return turnos;
    }

    @Override
    public Turno modificar(Turno turno) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_TURNO)){
            statement.setInt(4, turno.getId());
            statement.setInt(1, turno.getOdontologo().getId());
            statement.setInt(2, turno.getPaciente().getId());
            statement.setDate(3, turno.getFechaTurno());

            statement.executeUpdate();
            LOGGER.info("Se modificaron los datos del turno");
        }catch (Exception e){
            LOGGER.error("Se presentó error al modicar los datos", e);
            throw new Exception("Sucedio un error al modificar"+ e);
        }
        return turno;
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.DETELE_CUSTOM_TURNO)){
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            LOGGER.info("SE elimino el turno con id: " + id);
        }catch (Exception e){
            LOGGER.error("Se presentó error al eliminar el turno", e);
            throw new Exception("Sucedio un error al eliminar");
        }

    }

    @Override
    public Turno buscar(int id) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.TRAER_TURNO)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if(resultSet.getRow()==1) {
                Turno turno = new Turno();
                turno.setId(resultSet.getInt(1));
                turno.setFechaTurno(resultSet.getDate(4));

                int idOdontologo = resultSet.getInt(2);
                Odontologo odontologo = odontologoDao.buscar(idOdontologo);
                turno.setOdontologo(odontologo);

                int idPaciente = resultSet.getInt(3);
                Paciente paciente = pacienteDao.buscar(idPaciente);
                turno.setPaciente(paciente);

                return turno;

            }else throw new Exception("Error al buscar el turno");
        }catch (Exception e) {
            LOGGER.error("No fue posible encontrar el turno con id: " + id);
            throw new Exception("sucedio un error al buscar el turno");
        }
    }
}

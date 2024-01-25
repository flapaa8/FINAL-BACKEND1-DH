package DAOs;

import Entidades.Paciente;

import java.sql.Date;

public interface PacienteDAO {
    public abstract void registrarPaciente(Paciente paciente) throws Exception;
    public abstract void listarPacientes() throws Exception;
    public abstract void modificarPaciente(int id, Paciente paciente) throws Exception;
    public abstract void eliminarPaciente(int id)throws Exception;
    public abstract Paciente buscarPaciente(int id)throws Exception;
}

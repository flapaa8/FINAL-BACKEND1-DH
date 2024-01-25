package DAOs;

import Entidades.Turno;

public interface TurnoDAO {
    public abstract void asignarCita(Turno turno) throws Exception;
    public abstract void listarTurnos() throws Exception;
    public abstract void modificarTurno(int id, Turno turno) throws Exception;
    public abstract void eliminarTurno(int id) throws Exception;
    public abstract Turno buscarTurno(int id) throws Exception;

}

package Entidades;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Turno {
    private int id;
    private int idOdontologo;
    private int idPaciente;
    private Timestamp fechaHoraTurno;

    public Turno(int id, int idOdontologo, int idPaciente, Timestamp fechaHoraTurno) {
        this.id = id;
        this.idOdontologo = idOdontologo;
        this.idPaciente = idPaciente;
        this.fechaHoraTurno = fechaHoraTurno;
    }

    public Turno() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(int idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Timestamp getFechaHoraTurno() {
        return fechaHoraTurno;
    }

    public void setFechaHoraTurno(Timestamp fechaHoraTurno) {
        this.fechaHoraTurno = fechaHoraTurno;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", idOdontologo=" + idOdontologo +
                ", idPaciente=" + idPaciente +
                ", fechaHoraTurno=" + fechaHoraTurno +
                '}';
    }
}

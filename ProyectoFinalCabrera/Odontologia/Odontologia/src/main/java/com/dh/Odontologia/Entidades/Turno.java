package com.dh.Odontologia.Entidades;

import java.time.LocalDateTime;
import java.sql.Date;

public class Turno {
    private int id;
    private Odontologo odontologo;
    private Paciente paciente;
    private Date fechaTurno;

    public Turno() {
    }

    public Turno(int id, Odontologo odontologo, Paciente paciente, Date fechaTurno) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaTurno = fechaTurno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }


    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                ", fechaTurno=" + fechaTurno +
                '}';
    }
}


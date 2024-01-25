package com.dh.Odontologia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.time.LocalDateTime;

public class ActualizarTurnoDto {

    private int id;
    private PacienteDto paciente;
    private OdontologoDto odontologo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaTurno;


    public ActualizarTurnoDto() {
    }

    public ActualizarTurnoDto(int id, PacienteDto paciente, OdontologoDto odontologo, Date fechaTurno) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
}

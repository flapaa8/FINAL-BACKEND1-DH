package com.dh.Odontologia.dto;

import com.dh.Odontologia.Entidades.Domicilio;

import java.sql.Date;

public class PacienteDto {
    private int id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Date fechaAlta;

    public PacienteDto(int id, String nombre, String apellido, Domicilio domicilio, Date fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }


    public PacienteDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "PacienteDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio=" + domicilio +
                ", fechaAlta=" + fechaAlta +
                '}';
    }
}

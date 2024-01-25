package com.dh.Odontologia.Utils;

import com.dh.Odontologia.Entidades.Domicilio;
import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Entidades.Paciente;
import com.dh.Odontologia.Entidades.Turno;
import com.dh.Odontologia.dto.*;

public class Mapper {

    //map para odontologo

    public static Odontologo map(OdontologoDto dto){
        Odontologo odontologo = new Odontologo();

        odontologo.setApellido(dto.getApellido());
        odontologo.setNombre(dto.getNombre());
        odontologo.setId(dto.getId());
        odontologo.setMatricula(dto.getMatricula());
        return odontologo;

    }

    public static Odontologo map(ActualizarOdontologoDto dto){
        Odontologo odontologo = new Odontologo();

        odontologo.setApellido(dto.getApellido());
        odontologo.setNombre(dto.getNombre());
        odontologo.setId(dto.getId());
        odontologo.setMatricula(dto.getMatricula());
        return odontologo;

    }

    //map para paciente

    public static Paciente map(PacienteDto dto){
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();

        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setFechaAlta(dto.getFechaAlta());
        paciente.setDomicilio(domicilio);

        domicilio.setId(dto.getDomicilio().getId());
        domicilio.setCalle(dto.getDomicilio().getCalle());
        domicilio.setNumero(dto.getDomicilio().getNumero());
        domicilio.setCiudad(dto.getDomicilio().getCiudad());
        domicilio.setDepartamento(dto.getDomicilio().getDepartamento());


       return paciente;
    }

    public static Paciente map(ActualizarPacienteDto dto){
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();

        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setFechaAlta(dto.getFechaAlta());
        paciente.setDomicilio(domicilio);

        domicilio.setId(dto.getDomicilio().getId());
        domicilio.setCalle(dto.getDomicilio().getCalle());
        domicilio.setNumero(dto.getDomicilio().getNumero());
        domicilio.setCiudad(dto.getDomicilio().getCiudad());
        domicilio.setDepartamento(dto.getDomicilio().getDepartamento());

        return paciente;
    }

    //map para turno

    public static Turno map(TurnoDto dto){
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();

        turno.setId(dto.getId());
        turno.setFechaTurno(dto.getFechaTurno());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        odontologo.setId(dto.getOdontologo().getId());
        odontologo.setNombre(dto.getOdontologo().getNombre());
        odontologo.setApellido(dto.getOdontologo().getApellido());
        odontologo.setMatricula(dto.getOdontologo().getMatricula());

        paciente.setId(dto.getPaciente().getId());
        paciente.setNombre(dto.getPaciente().getNombre());
        paciente.setApellido(dto.getPaciente().getApellido());
        paciente.setDomicilio(dto.getPaciente().getDomicilio());
        paciente.setFechaAlta(dto.getPaciente().getFechaAlta());

        return turno;
    }

    public static Turno map(ActualizarTurnoDto dto){
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();

        turno.setId(dto.getId());
        turno.setFechaTurno(dto.getFechaTurno());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        odontologo.setId(dto.getOdontologo().getId());
        odontologo.setNombre(dto.getOdontologo().getNombre());
        odontologo.setApellido(dto.getOdontologo().getApellido());
        odontologo.setMatricula(dto.getOdontologo().getMatricula());

        paciente.setId(dto.getPaciente().getId());
        paciente.setNombre(dto.getPaciente().getNombre());
        paciente.setApellido(dto.getPaciente().getApellido());
        paciente.setDomicilio(dto.getPaciente().getDomicilio());
        paciente.setFechaAlta(dto.getPaciente().getFechaAlta());

        return turno;
    }







}

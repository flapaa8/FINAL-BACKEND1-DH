package com.dh.Odontologia.Servicios;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Entidades.Paciente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPaciente {
    private IDAO<Paciente> pacienteIDAO;
    private Logger LOGGER = Logger.getLogger(ServicioPaciente.class);

    @Autowired
    public ServicioPaciente(IDAO<Paciente> pacienteIDAO) {
        this.pacienteIDAO = pacienteIDAO;
    }

    public Paciente guardar(Paciente paciente) throws Exception {
        return pacienteIDAO.guardar(paciente);
    }

    public List<Paciente> listar() throws Exception {
        return pacienteIDAO.listar();
    }

    public Paciente modificar(Paciente paciente) throws Exception {
        return pacienteIDAO.modificar(paciente);
    }

    public boolean eliminar(int id) throws Exception {
        pacienteIDAO.eliminar(id);
        return true;
    }

    public Paciente buscar(int id) throws Exception {
       return pacienteIDAO.buscar(id);
    }
}

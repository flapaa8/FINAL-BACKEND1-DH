package com.dh.Odontologia.Servicios;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Entidades.Paciente;
import com.dh.Odontologia.Entidades.Turno;
import com.dh.Odontologia.dto.ActualizarTurnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTurno {
    private final IDAO<Turno> turnosDao;


    @Autowired
    public ServicioTurno(IDAO<Turno> turnosDao) {
        this.turnosDao = turnosDao;

    }


    public Turno guardar(Turno turno) throws Exception {
        return turnosDao.guardar(turno);
    }

    public List<Turno> listar()throws Exception{
        return turnosDao.listar();
    }

    public Turno modificar(Turno turno) throws Exception {
        return turnosDao.modificar(turno);
    }

    public boolean eliminar(int id) throws Exception {
        turnosDao.eliminar(id);
        return true;
    }

    public Turno buscar(int id) throws Exception {
        return turnosDao.buscar(id);
    }

}

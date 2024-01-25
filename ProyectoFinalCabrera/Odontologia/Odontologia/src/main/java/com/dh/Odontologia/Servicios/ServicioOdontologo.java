package com.dh.Odontologia.Servicios;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.DAOs.Impl.OdontologoDaoImpl;
import com.dh.Odontologia.Entidades.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioOdontologo {
    private IDAO<Odontologo> odontologoDao;
    private Logger LOGGER = Logger.getLogger(ServicioOdontologo.class);

    @Autowired
    public ServicioOdontologo(IDAO<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardar(Odontologo odontologo) throws Exception {
           return odontologoDao.guardar(odontologo);
    }

    public List<Odontologo> listar() throws Exception {
       return odontologoDao.listar();
    }

    public Odontologo modificar(Odontologo odontologo) throws Exception {
        return odontologoDao.modificar(odontologo);
    }

    public boolean eliminar(int id) throws Exception {
        odontologoDao.eliminar(id);
        return true;
    }

    public Odontologo buscar(int id) throws Exception {
        return odontologoDao.buscar(id);
    }

}

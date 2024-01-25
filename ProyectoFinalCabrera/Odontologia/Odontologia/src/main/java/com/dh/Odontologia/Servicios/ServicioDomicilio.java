package com.dh.Odontologia.Servicios;

import com.dh.Odontologia.DAOs.IDAO;
import com.dh.Odontologia.Entidades.Domicilio;
import com.dh.Odontologia.Entidades.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDomicilio {
    private IDAO<Domicilio> domicilioIDAO;
    private Logger LOGGER = Logger.getLogger(ServicioOdontologo.class);

    @Autowired
    public ServicioDomicilio(IDAO<Domicilio> domicilioIDAO) {
        this.domicilioIDAO = domicilioIDAO;
    }


    public Domicilio guardar(Domicilio domicilio) throws Exception {
        return domicilioIDAO.guardar(domicilio);
    }

    public List<Domicilio> listar() throws Exception {
        return domicilioIDAO.listar();
    }

    public Domicilio modificar(Domicilio domicilio) throws Exception {
        return domicilioIDAO.modificar(domicilio);
    }

    public boolean eliminar(int id) throws Exception {
        domicilioIDAO.eliminar(id);
        return true;
    }

    public Domicilio buscar(int id) throws Exception {
        return domicilioIDAO.buscar(id);
    }

}

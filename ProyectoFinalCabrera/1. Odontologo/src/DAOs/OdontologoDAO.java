package com.dh.odontologia.DAOs;

import Entidades.Odontologo;

public interface OdontologoDAO {

    public abstract void registrarOdontologo(Odontologo odontologo) throws Exception;
    public abstract String listarOdontologos()throws Exception;
    public abstract void modificarOdontologo (int id, Odontologo odontologo ) throws Exception;
    public abstract void eliminarOdontologo(int id) throws Exception;
    public abstract Odontologo buscarOdontologo(int id) throws Exception;

}

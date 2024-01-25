package com.dh.Odontologia.DAOs;

import com.dh.Odontologia.Entidades.Odontologo;

import java.util.List;

public interface IDAO<T> {
    public abstract T guardar(T t) throws Exception;
    public abstract List<T> listar()throws Exception;
    public abstract T modificar(T t) throws Exception;
    public abstract void eliminar(int id) throws Exception;
    public abstract T buscar(int id) throws Exception;
}

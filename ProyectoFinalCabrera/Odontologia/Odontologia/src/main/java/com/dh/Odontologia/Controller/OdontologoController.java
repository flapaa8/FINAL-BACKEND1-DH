package com.dh.Odontologia.Controller;

import com.dh.Odontologia.Entidades.Odontologo;
import com.dh.Odontologia.Servicios.ServicioOdontologo;
import com.dh.Odontologia.Utils.Mapper;
import com.dh.Odontologia.dto.ActualizarOdontologoDto;
import com.dh.Odontologia.dto.ActualizarPacienteDto;
import com.dh.Odontologia.dto.OdontologoDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    private final ServicioOdontologo servicioOdontologo;

    @Autowired
    public OdontologoController(ServicioOdontologo servicioOdontologo) {
        this.servicioOdontologo = servicioOdontologo;
    }


    /*
    - guardar- post
    - listar - get
    - modificar- put
    - buscar - get
    - eliminar - delete
    * */

    //1. guardar odontologo - crear odontologo - FUNCIONA
    @PostMapping
    public void guardarOdontologo(@RequestBody OdontologoDto request) throws Exception {
        LOGGER.info("Me llego: " +request);
        servicioOdontologo.guardar(Mapper.map(request));
    }

    //2. modificar odontologos - FUNCIONA

    @PutMapping
    public void modificarOdontologo(@RequestBody ActualizarOdontologoDto request) throws Exception {
        LOGGER.info("me llego: " +request);
        servicioOdontologo.modificar(Mapper.map(request));
    }

    //3. listar odontologos - FUNCIONA
    @GetMapping
    public ResponseEntity <List<Odontologo>> listarOdontologos() throws Exception {
        return new ResponseEntity<>(servicioOdontologo.listar(), HttpStatus.OK);
    }

    //4. buscar odontologo - FUNCIONA
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        return new ResponseEntity<>(servicioOdontologo.buscar(id), HttpStatus.OK);
    }
    //5. eliminar odontologo - FUNCIONA
    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        servicioOdontologo.eliminar(id);
    }

}

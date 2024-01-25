package com.dh.Odontologia.Controller;

import com.dh.Odontologia.Entidades.Turno;
import com.dh.Odontologia.Servicios.ServicioTurno;
import com.dh.Odontologia.Utils.Mapper;
import com.dh.Odontologia.dto.ActualizarPacienteDto;
import com.dh.Odontologia.dto.ActualizarTurnoDto;
import com.dh.Odontologia.dto.TurnoDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final Logger LOGGER = Logger.getLogger(TurnoController.class);
    private final ServicioTurno servicioTurno;

    @Autowired
    public TurnoController(ServicioTurno servicioTurno) {
        this.servicioTurno = servicioTurno;
    }

    //1. guardar turno - FUNCIONA (pte corregir hora de cita)
    @PostMapping
    public void guardarTurno(@RequestBody TurnoDto request) throws Exception {
        LOGGER.info("Me llego: " + request);
        servicioTurno.guardar(Mapper.map(request));
    }

    //2.modificar turno - FUNCIONA
    @PutMapping
    public void moficiarTurno(@RequestBody ActualizarTurnoDto request) throws Exception {
        LOGGER.info("Me llego: " + request);
        servicioTurno.modificar(Mapper.map(request));
    }

    //3. listar turnos - FUNCIONA
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() throws Exception {
        return new ResponseEntity<>(servicioTurno.listar(), HttpStatus.OK);
    }

    //4. buscar turno - FUNCIONA
    @GetMapping("{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        return new ResponseEntity<>(servicioTurno.buscar(id), HttpStatus.OK);
    }

    //5. eliminar turno - FUNCIONA
    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        servicioTurno.eliminar(id);
    }

}

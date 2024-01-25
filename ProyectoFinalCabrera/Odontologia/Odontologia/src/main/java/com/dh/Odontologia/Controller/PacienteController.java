package com.dh.Odontologia.Controller;

import com.dh.Odontologia.Entidades.Paciente;
import com.dh.Odontologia.Servicios.ServicioPaciente;
import com.dh.Odontologia.Utils.Mapper;
import com.dh.Odontologia.dto.ActualizarPacienteDto;
import com.dh.Odontologia.dto.PacienteDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final Logger LOGGER = Logger.getLogger(PacienteController.class);
    private final ServicioPaciente servicioPaciente;

    @Autowired
    public PacienteController(ServicioPaciente servicioPaciente) {
        this.servicioPaciente = servicioPaciente;
    }

    //1. guardar paciente - crear paciente - FUNCIONA (pendt corregir dto domicilio)
    @PostMapping
    public void guardarPaciente(@RequestBody PacienteDto request) throws Exception {
        LOGGER.info("Me llego: " + request);
        servicioPaciente.guardar(Mapper.map(request));
    }

    //2. modificar paciente - FUNCIONA
    @PutMapping
    public void modificarPaciente(@RequestBody ActualizarPacienteDto request) throws Exception {
        LOGGER.info("Me llego: " + request);
        servicioPaciente.modificar(Mapper.map(request));
    }

    //3. listar pacientes - FUNCIONA
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() throws Exception {
        return new ResponseEntity<>(servicioPaciente.listar(), HttpStatus.OK);
    }

    //4. buscar paciente - FUNCIONA
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        return new ResponseEntity<>(servicioPaciente.buscar(id), HttpStatus.OK);
    }

    //5. eliminar paciente - FUNCIONA
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable int id) throws Exception {
        LOGGER.info("se recibe info" + id);
        servicioPaciente.eliminar(id);
    }
}

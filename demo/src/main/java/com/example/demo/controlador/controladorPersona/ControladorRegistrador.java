package com.example.demo.controlador.controladorPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.servicio.servicioPersona.ServicioRegistrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ControladorRegistrador {


    private final ServicioRegistrador servicioRegistrador;

    @Autowired
    public ControladorRegistrador(ServicioRegistrador servicioRegistrador) {
        this.servicioRegistrador = servicioRegistrador;
    }


    @PostMapping(value = "registrarse/")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona registrarse(@Valid @RequestBody PersonaDTO personaDto, BindingResult validacion) {

        return servicioRegistrador.registrar(personaDto);
    }
}

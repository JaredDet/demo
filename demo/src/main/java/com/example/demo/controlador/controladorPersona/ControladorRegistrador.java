package com.example.demo.controlador.controladorPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.servicio.servicioPersona.ServicioRegistrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ControladorRegistrador {


    private final ServicioRegistrador servicioRegistrador;

    @Autowired
    public ControladorRegistrador(ServicioRegistrador servicioRegistrador) {
        this.servicioRegistrador = servicioRegistrador;
    }


    @PostMapping("registrarpersona/persona")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona registrarPersona(@RequestBody PersonaDTO personaDto) {

        return servicioRegistrador.registrarPersona(personaDto);
    }
}

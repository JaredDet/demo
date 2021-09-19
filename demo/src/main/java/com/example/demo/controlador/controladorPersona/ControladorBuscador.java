package com.example.demo.controlador.controladorPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.servicio.servicioPersona.ServicioBuscador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ControladorBuscador {

    private final ServicioBuscador servicioBuscador;

    @Autowired
    public ControladorBuscador(ServicioBuscador servicioBuscador) {
        this.servicioBuscador = servicioBuscador;
    }

    @GetMapping("filtrarusuariosporedad/{edad}")
    public List<Persona> getPersonasMayoresDe(@PathVariable int edad) {

        return servicioBuscador.getPersonasMayoresDe(edad);
    }

    @GetMapping("getpersonaporid/{id}")
    public Persona getPersonaPorId(@PathVariable long id) {

        return servicioBuscador.getPersonaPorID(id);
    }

    @GetMapping("getpersonaporcorreo/{correo}")
    public Persona getPersonaPorCorreo(@PathVariable String correo) {

        return servicioBuscador.getPersonaPorCorreo(correo);
    }

    @GetMapping("gettodos")
    public List<Persona> getTodos() {

        return servicioBuscador.getTodos();

    }
}


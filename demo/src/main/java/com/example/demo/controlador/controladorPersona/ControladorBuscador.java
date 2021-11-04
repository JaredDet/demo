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

    @GetMapping(value = "filtroEdad/")
    public List<Persona> getPersonasMayoresDe(@RequestParam int edad) {

        return servicioBuscador.buscarMayoresQue(edad);
    }

    @GetMapping(value = "buscaID/")
    public Persona getPersonaPorId(@RequestParam long id) {

        return servicioBuscador.buscarID(id);
    }

    @GetMapping(value = "buscaCorreo/")
    public Persona getPersonaPorCorreo(@RequestParam String correo) {

        return servicioBuscador.buscarCorreo(correo);
    }

    @GetMapping(value = "todos/")
    public List<Persona> getTodos() {

        return servicioBuscador.buscarTodos();

    }
}


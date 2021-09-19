package com.example.demo.controlador.controladorPersona;

import com.example.demo.servicio.servicioPersona.ServicioBorrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ControladorBorrador {

    private final ServicioBorrador servicioBorrador;

    @Autowired
    public ControladorBorrador(ServicioBorrador servicioBorrador) {
        this.servicioBorrador = servicioBorrador;
    }

    @DeleteMapping("deletepersona/{id}")
    public Boolean borrarPersonaPorId(@PathVariable long id) {

        return servicioBorrador.deletePersona(id);
    }
}

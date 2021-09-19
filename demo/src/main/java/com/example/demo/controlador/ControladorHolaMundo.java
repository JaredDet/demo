package com.example.demo.controlador;

import com.example.demo.servicio.ServicioHolaMundo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ControladorHolaMundo {

    private final ServicioHolaMundo servicioHolaMundo;

    @Autowired
    public ControladorHolaMundo(ServicioHolaMundo servicioHolaMundo) {
        this.servicioHolaMundo = servicioHolaMundo;
    }

    @GetMapping(value = "holamundo")
    public String saludar(){
        return servicioHolaMundo.saludar();
    }
}

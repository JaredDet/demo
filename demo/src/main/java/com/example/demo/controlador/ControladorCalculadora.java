package com.example.demo.controlador;

import com.example.demo.servicio.ServicioCalculadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ControladorCalculadora {

    private final ServicioCalculadora servicioCalculadora;

    @Autowired
    public ControladorCalculadora(ServicioCalculadora servicioCalculadora) {
        this.servicioCalculadora = servicioCalculadora;
    }

    @GetMapping(value = "/suma/{a}/{b}")
    public int sumar(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.sumar(a, b);
    }

    @GetMapping(value = "/resta/{a}/{b}")
    public int restar(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.restar(a, b);
    }

    @GetMapping(value = "/multiplicacion/{a}/{b}")
    public int multiplicar(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.multiplicar(a, b);
    }

    @GetMapping(value = "/division/{a}/{b}")
    public int dividir(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.dividir(a, b);
    }
}

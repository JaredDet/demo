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

    @GetMapping(value = "/suma")
    public double sumar(@RequestParam double a, @RequestParam double b) {
        return servicioCalculadora.sumar(a, b);
    }

    @GetMapping(value = "/resta")
    public double restar(@RequestParam double a, @RequestParam double b) {
        return servicioCalculadora.restar(a, b);
    }

    @GetMapping(value = "/multiplicacion")
    public double multiplicar(@RequestParam double a, @RequestParam double b) {
        return servicioCalculadora.multiplicar(a, b);
    }

    @GetMapping(value = "/division")
    public double dividir(@RequestParam double a, @RequestParam double b) {
        return servicioCalculadora.dividir(a, b);
    }

    @GetMapping(value = "/potencia_cuadrada")
    public double potenciarCuadrado(@RequestParam double a) {
        return servicioCalculadora.potenciarCuadrado(a);
    }

    @GetMapping(value = "/potencia_nesima")
    public double potenciarNesima(@RequestParam double a, @RequestParam int b) {
        return servicioCalculadora.potenciarNesima(a, b);
    }

    @GetMapping(value = "/inv_multiplicativo")
    public double sacarInvMultiplicativo(@RequestParam double a) {
        return servicioCalculadora.sacarInvMultiplicativo(a);
    }

    @GetMapping(value = "/raiz")
    public double sacarRaiz(@RequestParam double a) {
        return servicioCalculadora.sacarRaiz(a);
    }

    @GetMapping(value = "/modulo")
    public int sacarModulo(@RequestParam int a, @RequestParam int b) {
        return servicioCalculadora.sacarModulo(a, b);
    }
}

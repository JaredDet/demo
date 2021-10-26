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

    @GetMapping(value = "/potencia_cuadrada/{a}")
    public double potenciarCuadrado(@PathVariable int a) {
        return servicioCalculadora.potenciarCuadrado(a);
    }

    @GetMapping(value = "/potencia_nesima/{a}/{b}")
    public double potenciarNesima(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.potenciarNesima(a, b);
    }

    @GetMapping(value = "/inv_multiplicativo/{a}")
    public double sacarInvMultiplicativo(@PathVariable int a) {
        return servicioCalculadora.sacarInvMultiplicativo(a);
    }

    @GetMapping(value = "/raiz/{a}")
    public double sacarRaiz(@PathVariable int a) {
        return servicioCalculadora.sacarRaiz(a);
    }

    @GetMapping(value = "/modulo/{a}/{b}")
    public double sacarModulo(@PathVariable int a, @PathVariable int b) {
        return servicioCalculadora.sacarModulo(a, b);
    }
}

package com.example.demo.servicio;

import com.example.demo.excepcion.DivisionEntreCeroExcepction;
import org.springframework.stereotype.Service;

@Service
public class ServicioCalculadora {

    public Integer sumar(int a, int b) {
        return a + b;
    }

    public Integer restar(int a, int b) {
        return a - b;
    }

    public Integer multiplicar(int a, int b) {
        return a * b;
    }

    public Integer dividir(int a, int b) {

        if (b == 0) {
            throw new DivisionEntreCeroExcepction();
        }
        return a / b;
    }
}

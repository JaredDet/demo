package com.example.demo.servicio;

import com.example.demo.excepcion.DivisionEntreCeroException;
import com.example.demo.excepcion.EntradaNoValidaException;
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
            throw new DivisionEntreCeroException();
        }
        return a / b;
    }

    public double potenciarCuadrado(int a){
        return Math.pow(a, 2);
    }

    public double potenciarNesima(int a, int b){

        if(a == 0 && b < 0){
            throw new EntradaNoValidaException();
        }
        return Math.pow(a, b);
    }
}

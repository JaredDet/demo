package com.example.demo.servicio;

import com.example.demo.excepcion.DivisionEntreCeroException;
import com.example.demo.excepcion.EntradaNoValidaException;
import org.springframework.stereotype.Service;

@Service
public class ServicioCalculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {

        if (b == 0) {
            throw new DivisionEntreCeroException();
        }
        return a / b;
    }

    public double potenciarCuadrado(double a){
        return Math.pow(a, 2);
    }

    public double potenciarNesima(double a, int b){

        if(a == 0 && b < 0){
            throw new EntradaNoValidaException();
        }
        return Math.pow(a, b);
    }

    public double sacarInvMultiplicativo(double a){

        if(a == 0){
            throw new DivisionEntreCeroException();
        }
        return 1.0/a;
    }

    public double sacarRaiz(double a){

        if(a < 0){
            throw new EntradaNoValidaException();
        }
        return Math.sqrt(a);
    }

    public int sacarModulo(int a, int b){

        if(b == 0){
            throw new DivisionEntreCeroException();
        }

        if(a < 0 || b < 0){
            throw new EntradaNoValidaException();
        }
        return a % b;
    }
}

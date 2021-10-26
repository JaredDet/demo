package com.example.demo.excepcion;

public class EntradaNoValidaException extends RuntimeException{

    public EntradaNoValidaException(){
        super("Entrada no válida");
    }
}

package com.example.demo.excepcion;

public class DivisionEntreCeroException extends RuntimeException{

    public DivisionEntreCeroException(){
        super("No se puede dividir entre cero");
    }
}

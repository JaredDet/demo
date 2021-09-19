package com.example.demo.excepcion;

public class DivisionEntreCeroExcepction extends RuntimeException{

    public DivisionEntreCeroExcepction(){
        super("No se puede dividir entre cero");
    }
}

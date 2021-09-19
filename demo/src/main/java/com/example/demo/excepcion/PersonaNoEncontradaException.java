package com.example.demo.excepcion;

public class PersonaNoEncontradaException extends RuntimeException{

    public PersonaNoEncontradaException(String nombre) {
        super("No se ha encontrado la persona " + nombre + " en la base de datos");
    }

    public PersonaNoEncontradaException(long id){
        super("No se ha encontrado la persona con id " + id + " en la base de datos");
    }
}

package com.example.demo.excepcion;

public class NombreOcupadoException extends RuntimeException{

    public NombreOcupadoException(String nombre){
        super("El nombre de usuario " + nombre + " ya se encuentra en uso");
    }
}

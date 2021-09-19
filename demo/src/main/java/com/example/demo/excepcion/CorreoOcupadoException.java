package com.example.demo.excepcion;

public class CorreoOcupadoException extends RuntimeException{

    public CorreoOcupadoException(String correo){
        super("El correo " + correo + " ya se encuentra en uso");
    }
}

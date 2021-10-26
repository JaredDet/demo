package com.example.demo.servicio;

import org.springframework.stereotype.Service;

@Service
public class ServicioHolaMundo {

    public String saludar(){
        return "¡Hola, mundo!";
    }
}

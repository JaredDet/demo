package com.example.demo.servicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas del HolaMundo")
class ServicioHolaMundoTest {

    ServicioHolaMundo servicioHolaMundo;

    @BeforeEach
    void setUp() {
        servicioHolaMundo = new ServicioHolaMundo();
    }

    @Test
    void saludar() {
        var resultado = servicioHolaMundo.saludar();
        assertEquals("<h1>¡Hola, mundo!</h1>", resultado);
    }
}
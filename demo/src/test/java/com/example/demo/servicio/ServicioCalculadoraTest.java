package com.example.demo.servicio;


import com.example.demo.excepcion.DivisionEntreCeroExcepction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas de la calculadora")
class ServicioCalculadoraTest {

    ServicioCalculadora servicioCalculadora;

    @BeforeEach
    void setUp() {
        servicioCalculadora = new ServicioCalculadora();
    }

    final static int NUMERO_5 = 5;
    final static int NUMERO_2 = 2;
    final static int NUMERO_0 = 0;

    @Test
    void sumar() {
        var resultado = servicioCalculadora.sumar(NUMERO_5, NUMERO_2);
        assertEquals(7, resultado);
    }

    @Test
    void restar() {
        var resultado = servicioCalculadora.restar(NUMERO_5, NUMERO_2);
        assertEquals(3, resultado);
    }

    @Test
    void multiplicar() {
        var resultado = servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2);
        assertEquals(10, resultado);
    }

    @Test
    void dividir() {
        var resultado = servicioCalculadora.dividir(NUMERO_5, NUMERO_2);
        assertEquals(2, resultado);
    }

    @Test
    void dividirEntreCero() {
        assertThrows(DivisionEntreCeroExcepction.class,
                () -> servicioCalculadora.dividir(NUMERO_5, NUMERO_0));
    }
}
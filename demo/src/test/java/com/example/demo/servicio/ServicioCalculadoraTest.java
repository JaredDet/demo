package com.example.demo.servicio;


import com.example.demo.excepcion.DivisionEntreCeroException;
import com.example.demo.excepcion.EntradaNoValidaException;
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
    final static int NUMERO_NEGATIVO = -1;

    @Test
    @DisplayName("Test caso sumar")
    void sumar() {
        var resultado = servicioCalculadora.sumar(NUMERO_5, NUMERO_2);
        assertEquals(7, resultado);
    }

    @Test
    @DisplayName("Test caso restar")
    void restar() {
        var resultado = servicioCalculadora.restar(NUMERO_5, NUMERO_2);
        assertEquals(3, resultado);
    }

    @Test
    @DisplayName("Test caso multiplicar")
    void multiplicar() {
        var resultado = servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2);
        assertEquals(10, resultado);
    }

    @Test
    @DisplayName("Test caso dividir")
    void dividir() {
        var resultado = servicioCalculadora.dividir(NUMERO_5, NUMERO_2);
        assertEquals(2, resultado);
    }

    @Test
    @DisplayName("Test caso dividir entre cero")
    void dividirEntreCero() {
        assertThrows(DivisionEntreCeroException.class,
                () -> servicioCalculadora.dividir(NUMERO_5, NUMERO_0));
    }
    @Test
    @DisplayName("Test caso cinco al cuadrado")
    void potenciarCuadrado() {
        var resultado = servicioCalculadora.potenciarCuadrado(NUMERO_5);
        assertEquals(25, resultado);
    }

    @Test
    @DisplayName("Test caso cinco a la quinta potencia")
    void potenciarNesima() {
        var resultado = servicioCalculadora.potenciarNesima(NUMERO_5, NUMERO_5);
        assertEquals(3125, resultado);
    }

    @Test
    @DisplayName("Test caso cero a la cero")
    void potenciarCeroCero() {
        var resultado = servicioCalculadora.potenciarNesima(NUMERO_0, NUMERO_0);
        assertEquals(1, resultado);
    }
    @Test
    @DisplayName("Test caso cero potenciado a negativo")
    void potenciarCeroNegativo() {
        assertThrows(EntradaNoValidaException.class,
                () -> servicioCalculadora.potenciarNesima(NUMERO_0, NUMERO_NEGATIVO));
    }
}
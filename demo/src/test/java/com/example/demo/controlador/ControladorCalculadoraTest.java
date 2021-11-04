package com.example.demo.controlador;

import com.example.demo.excepcion.DivisionEntreCeroException;
import com.example.demo.excepcion.EntradaNoValidaException;
import com.example.demo.servicio.ServicioCalculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ControladorCalculadora.class)
@DisplayName("Pruebas de la calculadora")
class ControladorCalculadoraTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServicioCalculadora servicioCalculadora;

    final static int NUMERO_5 = 5;
    final static int NUMERO_2 = 2;
    final static int NUMERO_0 = 0;
    final static int NUMERO_4 = 4;
    final static int NUMERO_NEGATIVO = -1;

    @BeforeEach
    void setUp() {

        Mockito.when(servicioCalculadora.sumar(NUMERO_5, NUMERO_2))
                .thenReturn(7.0);
        Mockito.when(servicioCalculadora.restar(NUMERO_5, NUMERO_2))
                .thenReturn(3.0);
        Mockito.when(servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2))
                .thenReturn(10.0);
        Mockito.when(servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2))
                .thenReturn(10.0);
        Mockito.when(servicioCalculadora.dividir(NUMERO_5, NUMERO_2))
                .thenReturn(2.0);
        Mockito.when(servicioCalculadora.dividir(NUMERO_5, NUMERO_0))
                .thenThrow(new DivisionEntreCeroException());
        Mockito.when(servicioCalculadora.potenciarCuadrado(NUMERO_5))
                .thenReturn(25.0);
        Mockito.when(servicioCalculadora.potenciarNesima(NUMERO_5, NUMERO_5))
                .thenReturn(3125.0);
        Mockito.when(servicioCalculadora.potenciarNesima(NUMERO_0, NUMERO_0))
                .thenReturn(1.0);
        Mockito.when(servicioCalculadora.potenciarNesima(NUMERO_0, NUMERO_NEGATIVO))
                .thenThrow(new EntradaNoValidaException());
        Mockito.when(servicioCalculadora.sacarInvMultiplicativo(NUMERO_4))
                .thenReturn(0.25);
        Mockito.when(servicioCalculadora.sacarInvMultiplicativo(NUMERO_0))
                .thenThrow(new DivisionEntreCeroException());
        Mockito.when(servicioCalculadora.sacarRaiz(NUMERO_4))
                .thenReturn(2.0);
        Mockito.when(servicioCalculadora.sacarRaiz(NUMERO_NEGATIVO))
                .thenThrow(new EntradaNoValidaException());
        Mockito.when(servicioCalculadora.sacarModulo(NUMERO_5, NUMERO_2))
                .thenReturn(1);
        Mockito.when(servicioCalculadora.sacarModulo(NUMERO_5, NUMERO_0))
                .thenThrow(new DivisionEntreCeroException());
        Mockito.when(servicioCalculadora.sacarModulo(NUMERO_NEGATIVO, NUMERO_5))
                .thenThrow(new EntradaNoValidaException());
        Mockito.when(servicioCalculadora.sacarModulo(NUMERO_5, NUMERO_NEGATIVO))
                .thenThrow(new EntradaNoValidaException());
    }

    @Test
    @DisplayName("Test caso sumar")
    void sumar() throws Exception {
        mockMvc.perform(get("/api/suma/" + NUMERO_5 + "/" + NUMERO_2))
                .andExpect(status().isOk())
                .andExpect(content().string("7"));
    }

    @Test
    @DisplayName("Test caso restar")
    void restar() throws Exception {
        mockMvc.perform(get("/api/resta/" + NUMERO_5 + "/" + NUMERO_2))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    @DisplayName("Test caso multiplicar")
    void multiplicar() throws Exception {
        mockMvc.perform(get("/api/multiplicacion/" + NUMERO_5 + "/" + NUMERO_2))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    @DisplayName("Test caso dividir")
    void dividir() throws Exception {
        mockMvc.perform(get("/api/division/" + NUMERO_5 + "/" + NUMERO_2))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    @DisplayName("Test caso dividir entre cero")
    void dividirEntreCero() throws Exception {
        mockMvc.perform(get("/api/division/" + NUMERO_5 + "/" + NUMERO_0))
                .andExpect(status().isConflict())
                .andExpect(content().string(new DivisionEntreCeroException().getMessage()));
    }

    @Test
    @DisplayName("Test caso cinco al cuadrado")
    void potenciarCuadrado() throws Exception {
        mockMvc.perform(get("/api/potencia_cuadrada/" + NUMERO_5))
                .andExpect(status().isOk())
                .andExpect(content().string("25.0"));
    }

    @Test
    @DisplayName("Test caso cinco a la quinta potencia")
    void potenciarNesima() throws Exception {
        mockMvc.perform(get("/api/potencia_nesima/" + NUMERO_5 + "/" + NUMERO_5))
                .andExpect(status().isOk())
                .andExpect(content().string("3125.0"));
    }

    @Test
    @DisplayName("Test caso cero a la cero")
    void potenciarCeroCero() throws Exception {
        mockMvc.perform(get("/api/potencia_nesima/" + NUMERO_0 + "/" + NUMERO_0))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0"));
    }

    @Test
    @DisplayName("Test caso cero potenciado a negativo")
    void potenciarCeroNegativo() throws Exception {
        mockMvc.perform(get("/api/potencia_nesima/" + NUMERO_0 + "/" + NUMERO_NEGATIVO))
                .andExpect(status().isConflict())
                .andExpect(content().string(new EntradaNoValidaException().getMessage()));
    }

    @Test
    @DisplayName("Test inverso multiplicativo de cuatro")
    void sacarInvMultiplicativo() throws Exception {
        mockMvc.perform(get("/api/inv_multiplicativo/" + NUMERO_4))
                .andExpect(status().isOk())
                .andExpect(content().string("0.25"));
    }

    @Test
    @DisplayName("Test inverso multiplicativo de cero")
    void sacarInvMultiplicativoCero() throws Exception {
        mockMvc.perform(get("/api/inv_multiplicativo/" + NUMERO_0))
                .andExpect(status().isConflict())
                .andExpect(content().string(new DivisionEntreCeroException().getMessage()));
    }

    @Test
    @DisplayName("Test caso raíz positiva")
    void sacarRaiz() throws Exception {
        mockMvc.perform(get("/api/raiz/" + NUMERO_4))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    @DisplayName("Test caso raíz base negativa")
    void sacarRaizNegativa() throws Exception {
        mockMvc.perform(get("/api/raiz/" + NUMERO_NEGATIVO))
                .andExpect(status().isConflict())
                .andExpect(content().string(new EntradaNoValidaException().getMessage()));
    }

    @Test
    @DisplayName("Test caso módulo entre cinco y dos")
    void sacarModulo() throws Exception {
        mockMvc.perform(get("/api/modulo/" + NUMERO_5 + "/" + NUMERO_2))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0"));
    }

    @Test
    @DisplayName("Test caso módulo entre cinco y cero")
    void sacarModuloDivisionEntreCero() throws Exception {
        mockMvc.perform(get("/api/modulo/" + NUMERO_5 + "/" + NUMERO_0))
                .andExpect(status().isConflict())
                .andExpect(content().string(new DivisionEntreCeroException().getMessage()));
    }

    @Test
    @DisplayName("Test caso módulo entre menos uno y cinco")
    void sacarModuloNegativoA() throws Exception {
        mockMvc.perform(get("/api/modulo/" + NUMERO_NEGATIVO + "/" + NUMERO_5))
                .andExpect(status().isConflict())
                .andExpect(content().string(new EntradaNoValidaException().getMessage()));
    }

    @Test
    @DisplayName("Test caso módulo entre cinco y menos uno")
    void sacarModuloNegativoB() throws Exception {
        mockMvc.perform(get("/api/modulo/" + NUMERO_5 + "/" + NUMERO_NEGATIVO))
                .andExpect(status().isConflict())
                .andExpect(content().string(new EntradaNoValidaException().getMessage()));
    }
}

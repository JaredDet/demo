package com.example.demo.controlador;

import com.example.demo.excepcion.DivisionEntreCeroExcepction;
import com.example.demo.servicio.ServicioCalculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

    @BeforeEach
    void setUp() {

        Mockito.when(servicioCalculadora.sumar(NUMERO_5, NUMERO_2))
                .thenReturn(7);
        Mockito.when(servicioCalculadora.restar(NUMERO_5, NUMERO_2))
                .thenReturn(3);
        Mockito.when(servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2))
                .thenReturn(10);
        Mockito.when(servicioCalculadora.multiplicar(NUMERO_5, NUMERO_2))
                .thenReturn(10);
        Mockito.when(servicioCalculadora.dividir(NUMERO_5, NUMERO_2))
                .thenReturn(2);
        Mockito.when(servicioCalculadora.dividir(NUMERO_5, NUMERO_0))
                .thenThrow(new DivisionEntreCeroExcepction());
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
                .andExpect(content().string(new DivisionEntreCeroExcepction().getMessage()));
    }
}

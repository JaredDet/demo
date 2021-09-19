package com.example.demo.controlador;

import com.example.demo.servicio.ServicioHolaMundo;
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


@WebMvcTest(ControladorHolaMundo.class)
@DisplayName("Pruebas de la calculadora")
class ControladorHolaMundoTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServicioHolaMundo servicioHolaMundo;

    @BeforeEach
    void setUp() {
        Mockito.when(servicioHolaMundo.saludar())
                .thenReturn("<h1>¡Hola, mundo!</h1>");
    }

    @Test
    @DisplayName("Test enviar saludo")
    void saludar() throws Exception {
        mockMvc.perform(get("/api/holamundo"))
                .andExpect(status().isOk())
                .andExpect(content().string("<h1>¡Hola, mundo!</h1>"));
    }
}
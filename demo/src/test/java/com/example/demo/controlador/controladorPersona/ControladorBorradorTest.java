package com.example.demo.controlador.controladorPersona;

import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.servicio.servicioPersona.ServicioBorrador;
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

@WebMvcTest(ControladorBorrador.class)
@DisplayName("Pruebas del controlador de usuario: borrado")
class ControladorBorradorTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServicioBorrador servicioBorrador;

    private final Long ID = 0L;
    private final Long ID_INEXISTENTE = 100L;

    @BeforeEach
    void setUp() {
        Mockito.when(servicioBorrador.deletePersona(ID))
                .thenReturn(true);
        Mockito.when(servicioBorrador.deletePersona(ID_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(ID_INEXISTENTE));
    }

    @Test
    void borrarPersonaPorId() throws Exception {
        mockMvc.perform(delete("/api/deletepersona/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void borrarPersonaPorIdNoEncontrada() throws Exception {
        mockMvc.perform(delete("/api/deletepersona/" + ID_INEXISTENTE))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new PersonaNoEncontradaException(ID_INEXISTENTE).getMessage()));
    }
}
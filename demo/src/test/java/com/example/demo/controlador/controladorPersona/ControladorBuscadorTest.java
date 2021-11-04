package com.example.demo.controlador.controladorPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.servicio.servicioPersona.ServicioBuscador;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorBuscador.class)
@DisplayName("Pruebas del controlador de usuario: búsqueda")
class ControladorBuscadorTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServicioBuscador servicioBuscador;

    final static String NOMBRE_CERO = "Ninoska";
    final static String CORREO_CERO = "n.diaz@ufromail.cl";
    final static Integer EDAD_CERO = 20;

    final static String NOMBRE_UNO = "Jared";
    final static String CORREO_UNO = "jared27@gmail.com";
    final static Integer EDAD_UNO = 18;

    final static Long ID = 0L;

    final static String CORREO_INEXISTENTE = "jamarquez145@gmail.com";
    final static Long ID_INEXISTENTE = 500L;

    String jsonpersona_cero;
    String jsonpersona_uno;
    String jsonLista;
    String jsonListaVacia;

    @BeforeEach
    void setUp() throws JsonProcessingException {

        var mapper = new ObjectMapper();

        var persona = new Persona();
        persona.setNombre(NOMBRE_CERO);
        persona.setCorreo(CORREO_CERO);
        persona.setEdad(EDAD_CERO);

        var persona1 = new Persona();
        persona1.setNombre(NOMBRE_UNO);
        persona1.setCorreo(CORREO_UNO);
        persona1.setEdad(EDAD_UNO);

        Mockito.when(servicioBuscador.buscarID(ID))
                .thenReturn(persona);
        Mockito.when(servicioBuscador.buscarID(ID_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(ID_INEXISTENTE));
        Mockito.when(servicioBuscador.buscarCorreo(CORREO_CERO))
                .thenReturn(persona);
        Mockito.when(servicioBuscador.buscarCorreo(CORREO_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(CORREO_INEXISTENTE));
        Mockito.when(servicioBuscador.buscarTodos())
                .thenReturn(List.of(persona, persona1));
        Mockito.when(servicioBuscador.buscarMayoresQue(10))
                .thenReturn(List.of(persona, persona1));
        Mockito.when(servicioBuscador.buscarMayoresQue(200))
                .thenReturn(Collections.emptyList());

        jsonpersona_cero = mapper.writeValueAsString(persona);
        jsonpersona_uno = mapper.writeValueAsString(persona1);
        jsonLista = mapper.writeValueAsString(List.of(persona, persona1));
        jsonListaVacia = mapper.writeValueAsString(Collections.emptyList());
    }

    @Test
    void getPersonaPorId() throws Exception {

        mockMvc.perform(get("/api/getpersonaporid/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonpersona_cero));
    }

    @Test
    void getPersonaPorIdNoEncontrada() throws Exception {

        mockMvc.perform(get("/api/getpersonaporid/" + ID_INEXISTENTE))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new PersonaNoEncontradaException(ID_INEXISTENTE).getMessage()));
    }

    @Test
    void getPersonaPorCorreo() throws Exception {

        mockMvc.perform(get("/api/getpersonaporcorreo/" + CORREO_CERO))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonpersona_cero));
    }

    @Test
    void getPersonaPorCorreoNoEncontrada() throws Exception {

        mockMvc.perform(get("/api/getpersonaporcorreo/" + CORREO_INEXISTENTE))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new PersonaNoEncontradaException(CORREO_INEXISTENTE).getMessage()));
    }

    @Test
    void getTodos() throws Exception {

        mockMvc.perform(get("/api/gettodos/"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonLista));
    }

    @Test
    void getPersonasMayoresDe() throws Exception {

        mockMvc.perform(get("/api/filtrarusuariosporedad/" + 10))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonLista));
    }

    @Test
    void getPersonasMayoresDeListaVacia() throws Exception {

        mockMvc.perform(get("/api/filtrarusuariosporedad/" + 200))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonListaVacia));
    }
}
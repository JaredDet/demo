/*
package com.example.demo.controlador.controladorPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.excepcion.CorreoOcupadoException;
import com.example.demo.excepcion.NombreOcupadoException;
import com.example.demo.servicio.servicioPersona.ServicioRegistrador;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ControladorRegistrador.class)
@DisplayName("Pruebas del controlador de usuario: registro")
class ControladorRegistradorTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServicioRegistrador servicioRegistrador;


    final static String NOMBRE = "Ninoska";
    final static String CORREO = "n.diaz@ufromail.cl";
    final static Integer EDAD = 21;

    final static String NOMBRE_OCUPADO = "Jared";
    final static String CORREO_OCUPADO = "jamarquez145@gmail.com";

    String jsonpersonaNuevo;
    String jsonCorreoOcupado;
    String jsonNombreOcupado;
    String jsonpersonaRegistrado;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        var mapper = new ObjectMapper();

        var personaNuevo = new PersonaDTO(NOMBRE, CORREO, EDAD);
        var personaNuevoNombreOcupado = new PersonaDTO(NOMBRE_OCUPADO, CORREO, EDAD);
        var personaCorreoOcupado = new PersonaDTO(NOMBRE, CORREO_OCUPADO, EDAD);

        var personaRegistrado = new Persona();
        personaRegistrado.setNombre(NOMBRE);
        personaRegistrado.setCorreo(CORREO);
        personaRegistrado.setEdad(EDAD);

        Mockito.when(servicioRegistrador.registrar(personaNuevo))
                .thenReturn(personaRegistrado);
        Mockito.when(servicioRegistrador.registrar(personaNuevoNombreOcupado))
                .thenThrow(new NombreOcupadoException(NOMBRE_OCUPADO));
        Mockito.when(servicioRegistrador.registrar(personaCorreoOcupado))
                .thenThrow(new CorreoOcupadoException(CORREO_OCUPADO));

        jsonpersonaNuevo = mapper.writeValueAsString(personaNuevo);
        jsonCorreoOcupado = mapper.writeValueAsString(personaCorreoOcupado);
        jsonNombreOcupado = mapper.writeValueAsString(personaNuevoNombreOcupado);
        jsonpersonaRegistrado = mapper.writeValueAsString(personaRegistrado);
    }

    @Test
    void registrarPersona() throws Exception {
        mockMvc.perform(post("/api/registrarpersona/persona")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonpersonaNuevo)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(jsonpersonaRegistrado));
    }

    @Test
    void registrarPersonaCorreoOcupado() throws Exception {
        mockMvc.perform(post("/api/registrarpersona/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCorreoOcupado)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isConflict())
                .andExpect(content().string(new CorreoOcupadoException(CORREO_OCUPADO).getMessage()));
    }

    @Test
    void registrarPersonaNombreOcupado() throws Exception {
        mockMvc.perform(post("/api/registrarpersona/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNombreOcupado)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isConflict())
                .andExpect(content().string(new NombreOcupadoException(NOMBRE_OCUPADO).getMessage()));
    }
}
 */
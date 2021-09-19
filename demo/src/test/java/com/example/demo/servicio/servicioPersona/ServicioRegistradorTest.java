package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.excepcion.CorreoOcupadoException;
import com.example.demo.excepcion.NombreOcupadoException;
import com.example.demo.repositorio.RepositorioPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas del registrador")
class ServicioRegistradorTest {

    @MockBean
    RepositorioPersona repositorioPersona;

    final static String NOMBRE = "Ninoska";
    final static String CORREO = "n.diaz@ufromail.cl";
    final static Integer EDAD = 20;

    final static String NOMBRE_OCUPADO = "Jared";
    final static String CORREO_OCUPADO = "jamarquez145@gmail.com";

    private PersonaDTO persona;
    private PersonaDTO personaNombreOcupado;
    private PersonaDTO personaCorreoOcupado;

    private Persona personaRegistrada;
    ServicioRegistrador servicioRegistrador;

    @BeforeEach
    void setUp() {

        servicioRegistrador = new ServicioRegistrador(repositorioPersona);

        persona = new PersonaDTO(NOMBRE, CORREO, EDAD);

        personaRegistrada = new Persona();
        personaRegistrada.setNombre(NOMBRE);
        personaRegistrada.setCorreo(CORREO);
        personaRegistrada.setEdad(EDAD);

        personaNombreOcupado = new PersonaDTO(NOMBRE_OCUPADO, CORREO, EDAD);
        personaCorreoOcupado = new PersonaDTO(NOMBRE, CORREO_OCUPADO, EDAD);

        Mockito.when(repositorioPersona.existsByNombre(NOMBRE_OCUPADO))
                        .thenReturn(true);
        Mockito.when(repositorioPersona.existsByCorreo(CORREO_OCUPADO))
                        .thenReturn(true);
        Mockito.when(repositorioPersona.save(personaRegistrada))
                .thenReturn(personaRegistrada);
    }

    @Test
    void registrarPersona() {
        var resultado = servicioRegistrador.registrarPersona(persona);
        assertEquals(personaRegistrada, resultado);
    }

    @Test
    void registrarPersonaPersonaNombreOcupado() {
        assertThrows(NombreOcupadoException.class,
                () -> servicioRegistrador.registrarPersona(personaNombreOcupado));
    }

    @Test
    void registrarPersonaPersonaCorreoOcupado() {
        assertThrows(CorreoOcupadoException.class,
                () -> servicioRegistrador.registrarPersona(personaCorreoOcupado));
    }
}
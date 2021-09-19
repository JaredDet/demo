package com.example.demo.repositorio;

import com.example.demo.dominio.Persona;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Pruebas del repositorio")
class RepositorioPersonaTest {

    @Autowired
    RepositorioPersona repositorioPersona;

    final File LISTA_PERSONAS = Paths.get("src", "test", "resources", "personaPrueba.json").toFile();

    final static String CORREO = "raul45@gmail.com";
    final static String CORREO_FAKE = "correofake@gmail.com";
    Persona[] personas;

    @BeforeEach
    void setUp() throws IOException {

        personas = new ObjectMapper().readValue(LISTA_PERSONAS, Persona[].class);
        Arrays.stream(personas).forEach(repositorioPersona::save);
    }

    @AfterEach
    void cleanUp() {
        repositorioPersona.deleteAll();
    }

    @Test
    void findByCorreo() {

        var resultado = repositorioPersona.findByCorreo(CORREO);
        assertEquals(personas[1], resultado.orElseThrow(() -> new PersonaNoEncontradaException(CORREO)));
    }

    @Test
    void findByCorreoNoEncontrado() {

        assertThrows(PersonaNoEncontradaException.class,
                () -> repositorioPersona
                        .findByCorreo(CORREO_FAKE)
                        .orElseThrow(() -> new PersonaNoEncontradaException(CORREO_FAKE)));
    }

    @Test
    void existsByNombre() {
        assertTrue(repositorioPersona.existsByNombre("John"));
    }

    @Test
    void NotexistsByNombre() {
        assertFalse(repositorioPersona.existsByNombre("Nombre_Fake"));
    }

    @Test
    void existsByCorreo() {
        assertTrue(repositorioPersona.existsByCorreo(CORREO));
    }

    @Test
    void NotexistsByCorreo() {
        assertFalse(repositorioPersona.existsByCorreo(CORREO_FAKE));
    }

    @Test
    void findByEdadGreaterThan() {

        var resultado = repositorioPersona.findByEdadGreaterThan(15);
        assertEquals(List.of(personas[0], personas[2]), resultado);
    }

    @Test
    void findByEdadGreaterThanListaVacia() {

        var resultado = repositorioPersona.findByEdadGreaterThan(200);
        assertTrue(resultado.isEmpty());
    }
}
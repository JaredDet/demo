package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.excepcion.CorreoOcupadoException;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.repositorio.RepositorioPersona;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas del borrador")
public class ServicioBorradorTest {

    @Autowired
    RepositorioPersona repositorioPersona;

    ServicioBuscador servicioBuscador;
    ServicioBorrador servicioBorrador;

    final static long ID = 4L;
    final static long ID_INEXISTENTE = 7L;

    final File LISTA_PERSONAS = Paths.get("src", "test", "resources", "personaPrueba.json").toFile();
    Persona[] personas;

    @BeforeEach
    void setUp() throws IOException {

        servicioBuscador = new ServicioBuscador(repositorioPersona);
        servicioBorrador = new ServicioBorrador(repositorioPersona, servicioBuscador);
        personas = new ObjectMapper().readValue(LISTA_PERSONAS, Persona[].class);
        Arrays.stream(personas).forEach(repositorioPersona::save);
    }


    @Test
    void borrarID() {

        servicioBorrador.borrar(ID);
        assertThrows(PersonaNoEncontradaException.class, () -> servicioBuscador.buscarID(ID));
    }

    @Test
    void borrarNoExistente() {
        assertThrows(PersonaNoEncontradaException.class, () -> servicioBorrador.borrar(ID_INEXISTENTE));
    }
}

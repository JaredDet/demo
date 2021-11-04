package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.repositorio.RepositorioPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas del buscador")
class ServicioBuscadorTest {

    @MockBean
    RepositorioPersona repositorioPersona;


    final static String NOMBRE_CERO = "Ninoska";
    final static String CORREO_CERO = "n.diaz@ufromail.cl";
    final static Integer EDAD_CERO = 20;

    final static String NOMBRE_UNO = "Jared";
    final static String CORREO_UNO = "jared27@gmail.com";
    final static Integer EDAD_UNO = 18;

    final static Long ID = 0L;

    final static String CORREO_INEXISTENTE = "jamarquez145@gmail.com";
    final static Long ID_INEXISTENTE = 500L;

    Persona persona;
    Persona persona1;
    List<Persona> personaLista;

    ServicioBuscador servicioBuscador;

    @BeforeEach
    void setUp() {

        servicioBuscador = new ServicioBuscador(repositorioPersona);

        persona = new Persona();
        persona.setNombre(NOMBRE_CERO);
        persona.setCorreo(CORREO_CERO);
        persona.setEdad(EDAD_CERO);

        persona1 = new Persona();
        persona1.setNombre(NOMBRE_UNO);
        persona1.setCorreo(CORREO_UNO);
        persona1.setEdad(EDAD_UNO);

        personaLista = List.of(persona, persona1);

        Mockito.when(repositorioPersona.findById(ID))
                .thenReturn(Optional.of(persona));
        Mockito.when(repositorioPersona.findById(ID_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(ID_INEXISTENTE));
        Mockito.when(repositorioPersona.findByCorreo(CORREO_CERO))
                .thenReturn(Optional.of(persona));
        Mockito.when(repositorioPersona.findByCorreo(CORREO_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(CORREO_INEXISTENTE));
        Mockito.when(repositorioPersona.findAll())
                .thenReturn(personaLista);
        Mockito.when(repositorioPersona.findByEdadGreaterThan(10))
                .thenReturn(personaLista);
        Mockito.when(repositorioPersona.findByEdadGreaterThan(200))
                .thenReturn(Collections.emptyList());
    }

    @Test
    void getPersonaPorID() {

        var resultado = servicioBuscador.buscarID(ID);
        assertEquals(persona, resultado);
    }

    @Test
    void getPersonaPorIDNoEncontrado() {

        assertThrows(PersonaNoEncontradaException.class, () -> servicioBuscador.buscarID(ID_INEXISTENTE));
    }

    @Test
    void getPersonaPorCorreo() {

        var resultado = servicioBuscador.buscarCorreo(CORREO_CERO);
        assertEquals(persona, resultado);
    }

    @Test
    void getPersonaPorCorreoNoEncontrado() {

        assertThrows(PersonaNoEncontradaException.class, () -> servicioBuscador.buscarCorreo(CORREO_INEXISTENTE));
    }

    @Test
    void getTodos() {

        var resultado = servicioBuscador.buscarTodos();
        assertEquals(personaLista, resultado);
    }

    @Test
    void getPersonasMayoresDe() {

        var resultado = servicioBuscador.buscarMayoresQue(10);
        assertEquals(personaLista, resultado);
    }

    @Test
    void getPersonasMayoresDeListaVacia() {

        var resultado = servicioBuscador.buscarMayoresQue(200);
        assertTrue(resultado.isEmpty());
    }
}
package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.excepcion.NombreOcupadoException;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.repositorio.RepositorioPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Pruebas del borrador")
class ServicioBorradorTest {

    @MockBean
    RepositorioPersona repositorioPersona;
    @MockBean
    ServicioBuscador servicioBuscador;

    private final Long ID = 0L;
    private final Long ID_INEXISTENTE = 100L;
    ServicioBorrador servicioBorrador;

    @BeforeEach
    void setUp() {

        servicioBorrador = new ServicioBorrador(repositorioPersona, servicioBuscador);

        final String NOMBRE = "Ninoska";
        final String CORREO = "n.diaz@ufromail.cl";
        final int EDAD = 20;

        var persona = new Persona(NOMBRE, CORREO, EDAD);

        Mockito.when(servicioBuscador.getPersonaPorID(ID))
                .thenReturn(persona);
        Mockito.when(servicioBuscador.getPersonaPorID(ID_INEXISTENTE))
                .thenThrow(new PersonaNoEncontradaException(ID_INEXISTENTE));
    }

    @Test
    void deletePersona() {

        var resultado = servicioBorrador.deletePersona(ID);
        assertTrue(resultado);
    }

    @Test
    void deletePersonaNoEncontrada() {

        assertThrows(PersonaNoEncontradaException.class,
                () -> servicioBorrador.deletePersona(ID_INEXISTENTE));
    }
}
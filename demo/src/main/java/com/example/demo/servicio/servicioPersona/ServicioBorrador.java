package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioBorrador {

    private final RepositorioPersona repositorioPersona;
    private final ServicioBuscador servicioBuscador;

    @Autowired
    public ServicioBorrador(RepositorioPersona repositorioPersona, ServicioBuscador servicioBuscador) {
        this.repositorioPersona = repositorioPersona;
        this.servicioBuscador = servicioBuscador;
    }

    public Persona borrar(Long id) {

        var persona = servicioBuscador.buscarID(id);
        repositorioPersona.delete(persona);
        return persona;
    }
}

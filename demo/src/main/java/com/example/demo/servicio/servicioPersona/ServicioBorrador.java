package com.example.demo.servicio.servicioPersona;

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

    public Boolean deletePersona(Long id) {

        var persona = servicioBuscador.getPersonaPorID(id);
        repositorioPersona.delete(persona);
        return true;
    }
}

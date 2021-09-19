package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.excepcion.PersonaNoEncontradaException;
import com.example.demo.repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioBuscador {

    private final RepositorioPersona repositorioPersona;

    @Autowired
    public ServicioBuscador(RepositorioPersona repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public List<Persona> getPersonasMayoresDe(int edad){
        return repositorioPersona.findByEdadGreaterThan(edad);
    }

    public Persona getPersonaPorID(long id){
        return repositorioPersona.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));
    }

    public Persona getPersonaPorCorreo(String correo){
        return repositorioPersona.findByCorreo(correo)
                .orElseThrow(() -> new PersonaNoEncontradaException(correo));
    }

    public List<Persona> getTodos(){
        return repositorioPersona.findAll();
    }
}

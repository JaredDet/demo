package com.example.demo.servicio.servicioPersona;

import com.example.demo.dominio.Persona;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.excepcion.CorreoOcupadoException;
import com.example.demo.excepcion.NombreOcupadoException;
import com.example.demo.repositorio.RepositorioPersona;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ServicioRegistrador {

    private final RepositorioPersona repositorioPersona;

    @Autowired
    public ServicioRegistrador(RepositorioPersona repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public Persona registrar(PersonaDTO personaDto) {

        if(correoOcupado(personaDto)){
            throw new CorreoOcupadoException(personaDto.getCorreo());
        }

        if (nombreOcupado(personaDto)){
            throw new NombreOcupadoException(personaDto.getNombre());
        }

        var persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setCorreo(personaDto.getCorreo());
        persona.setEdad(personaDto.getEdad());
        persona.setCumpleanos(personaDto.getCumpleanos());

        return repositorioPersona.save(persona);
    }

    private boolean correoOcupado(PersonaDTO personaDto) {
        return repositorioPersona.existsByCorreo(personaDto.getCorreo());
    }

    private boolean nombreOcupado(PersonaDTO personaDto) {
        return repositorioPersona.existsByNombre(personaDto.getNombre());
    }
}

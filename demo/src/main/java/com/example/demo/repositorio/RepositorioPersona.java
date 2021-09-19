package com.example.demo.repositorio;

import com.example.demo.dominio.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioPersona extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCorreo(String correo);
    Boolean existsByNombre(String nombre);
    Boolean existsByCorreo(String correo);
    List<Persona> findByEdadGreaterThan (int edad);
}

package com.example.demo.dto;

import com.example.demo.validacion.cumpleanos.Cumpleanos;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class PersonaDTO {

    private final String nombre;
    private final String correo;
    private final int edad;
    private final LocalDate cumpleanos;
}

package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonaDTO {

    private final String nombre;
    private final String correo;
    private final int edad;
}

package com.example.demo.dominio;

import com.example.demo.validacion.cumpleanos.Cumpleanos;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Persona")
@Data
@Cumpleanos
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(updatable = false)
    private final String fechaRegistro = String.valueOf(LocalDate.now());

    @Min(value = 18, message = "Debe ser mayor de edad")
    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private LocalDate cumpleanos;
}

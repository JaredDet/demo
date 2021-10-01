package com.example.demo.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "Persona")
@Data
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nombre;

    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(updatable = false)
    private final String fechaRegistro = String.valueOf(LocalDate.now());

    @Column(nullable = false)
    private Integer edad;

}

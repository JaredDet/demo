package com.example.demo.validacion.cumpleanos;

import com.example.demo.dominio.Persona;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ValidadorCumpleanos implements ConstraintValidator<Cumpleanos, Persona> {

    @Override
    public void initialize(Cumpleanos constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Persona persona, ConstraintValidatorContext constraintValidatorContext) {
        var p = Period.between(persona.getCumpleanos(), LocalDate.now());
        return p.getYears() == persona.getEdad();
    }
}

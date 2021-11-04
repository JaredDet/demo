package com.example.demo.validacion.cumpleanos;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ValidadorCumpleanos.class)
@Target({ElementType.TYPE, ElementType.FIELD}) //destino de la validación puede ser un campo o un método
@Retention(RetentionPolicy.RUNTIME) //checkea la anotación en tiempo de ejecución

public @interface Cumpleanos {

    String message() default "La fecha de cumpleaños no coincide con la edad";
    Class<?>[] groups() default {}; //validar en cascada, mediante grupos
    Class<? extends Payload>[] payload() default {}; //metadatos
}

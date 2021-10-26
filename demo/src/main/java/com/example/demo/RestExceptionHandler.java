package com.example.demo;

import com.example.demo.excepcion.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DivisionEntreCeroException.class, EntradaNoValidaException.class,NombreOcupadoException.class, CorreoOcupadoException.class})
    public ResponseEntity<Object> manejarConflicto(RuntimeException e, WebRequest peticion) {
        var cuerpo = e.getMessage();
        return handleExceptionInternal(e, cuerpo, new HttpHeaders(), HttpStatus.CONFLICT, peticion);
    }

    @ExceptionHandler(value = {PersonaNoEncontradaException.class})
    public ResponseEntity<Object> manejarNoEncontrado(RuntimeException e, WebRequest peticion) {
        String cuerpo = e.getMessage();
        return handleExceptionInternal(e, cuerpo, new HttpHeaders(), HttpStatus.NOT_FOUND, peticion);
    }
}

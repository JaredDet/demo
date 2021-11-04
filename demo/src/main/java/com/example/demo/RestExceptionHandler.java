package com.example.demo;

import com.example.demo.excepcion.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DivisionEntreCeroException.class, EntradaNoValidaException.class, NombreOcupadoException.class, CorreoOcupadoException.class})
    public ResponseEntity<Object> manejarConflicto(RuntimeException e, WebRequest peticion) {
        var cuerpo = e.getMessage();
        return handleExceptionInternal(e, cuerpo, new HttpHeaders(), HttpStatus.CONFLICT, peticion);
    }

    @ExceptionHandler(value = PersonaNoEncontradaException.class)
    public ResponseEntity<Object> manejarNoEncontrado(RuntimeException e, WebRequest peticion) {
        var cuerpo = e.getMessage();
        return handleExceptionInternal(e, cuerpo, new HttpHeaders(), HttpStatus.NOT_FOUND, peticion);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> manejarValidaciones(RuntimeException e, WebRequest peticion) throws JsonProcessingException {
        var mapa = new TreeMap<String, String>();
        var restricciones = new ArrayList<>(((ConstraintViolationException) e).getConstraintViolations());

        for(var restriccion: restricciones){
            mapa.put("Error " + (restricciones.indexOf(restriccion) + 1), restriccion.getMessage());
        }

        var cuerpo = new ObjectMapper().writeValueAsString(mapa).replace("{", "").replace("}", "");

        return handleExceptionInternal(e, cuerpo, new HttpHeaders(), HttpStatus.BAD_REQUEST, peticion);
    }
}

package com.ajedrez.AjedrezAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //para cuando no se encuentra (nulo o vacio)
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //cuando los datos enviados están mal
    @ExceptionHandler(Validacion.class)
    public ResponseEntity<String> handleValidacion(Validacion ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //usado para cuando los datos no están mal, pero en el caso no lo permite enviar. Usado nomas en delete
    @ExceptionHandler(Conflicto.class)
    public ResponseEntity<String> handleConflicto(Conflicto ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
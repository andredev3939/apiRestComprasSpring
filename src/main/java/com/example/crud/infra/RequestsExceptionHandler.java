package com.example.crud.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Indica que a classe lida com exceções

public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class) // O parâmetro é a exceção
    public ResponseEntity threat404(){
        return ResponseEntity.badRequest().body("Dado não encontrado");
    }
}

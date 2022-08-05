package br.com.meli.desafio_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerPersonalized {

    @ExceptionHandler(ExProductNotFound.class)
    public ResponseEntity<ExceptionDetails> handlerProductNotFound(ExProductNotFound ex) {
        return new ResponseEntity<>(
            ExceptionDetails.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),
            HttpStatus.NOT_FOUND
        );
    }
}

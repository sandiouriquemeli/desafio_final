package br.com.meli.desafio_final.handlerException;


import br.com.meli.desafio_final.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponseDetails> handlerNotFound(NotFound notFound) {
        return new ResponseEntity<>(
                ErrorResponseDetails.builder()
                        .title("NotFound")
                        .status(HttpStatus.NOT_FOUND.value())
                        .link("https://http.cat/404")
                        .message(notFound.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ErrorResponseDetails> handlerBadRequest(BadRequest badRequest) {
        return new ResponseEntity<>(
                ErrorResponseDetails.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .link("https://http.cat/400")
                        .message(badRequest.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<ErrorResponseDetails> handlerUnauthorized(Unauthorized unauthorized) {
        return new ResponseEntity<>(
                ErrorResponseDetails.builder()
                        .title("Unauthorized")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .link("https://http.cat/401")
                        .message(unauthorized.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotAcceptable.class)
    public ResponseEntity<ErrorResponseDetails> handlerNotAcceptable(NotAcceptable notAcceptable) {
        return new ResponseEntity<>(
                ErrorResponseDetails.builder()
                        .title("Not Acceptable")
                        .status(HttpStatus.NOT_ACCEPTABLE.value())
                        .link("https://http.cat/406")
                        .message(notAcceptable.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_ACCEPTABLE);
    }
}

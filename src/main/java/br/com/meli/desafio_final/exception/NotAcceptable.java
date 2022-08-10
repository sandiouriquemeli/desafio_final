package br.com.meli.desafio_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptable extends RuntimeException{

    public NotAcceptable(String message) {
        super(message);
    }
}

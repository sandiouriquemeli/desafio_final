package br.com.meli.desafio_final.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDetails {
    private String title;
    private int status;
    private String link;
    private String message;
    private LocalDateTime timestamp;
}

package br.com.meli.desafio_final.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorBodyValidationsResponse {
    private String title;
    private String message;
    private String fields;
    private LocalDateTime timestamp;
}

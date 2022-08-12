package br.com.meli.desafio_final.handlerException;

import br.com.meli.desafio_final.exception.ErrorBodyValidationsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerBodyExeptions extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        return new ResponseEntity<>(
                ErrorBodyValidationsResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .title("Campos inválidos")
                        .fields(errors.stream().map(
                                FieldError::getField).collect(Collectors.joining("; ")))
                        .message(errors.stream().map(
                                FieldError::getDefaultMessage).collect(Collectors.joining("; ")))
                        .build()
                , status
        );
    }
}

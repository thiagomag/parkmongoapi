package com.fiap.parkmongoapi.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fiap.parkmongoapi.dto.ResponseError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> handleEntityNotFoundException(EntityNotFoundException ex) {
        ResponseError error = new ResponseError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseError> handleConflictException(ConflictException ex) {
        ResponseError error = new ResponseError(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                List.of(ex.getMessage()) // Lista de erros com um único erro
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintViolationException ex) {
        // Extrai as mensagens de erro de validação
        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .distinct().toList();

        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors
        );

        // Retorna a resposta com o status 400 (Bad Request)
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Extrai todas as mensagens de erro de validação
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format(
                        "Erro no campo '%s': %s",
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        // Cria o objeto de erro
        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors
        );

        // Retorna a resposta com todas as mensagens
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        List<String> errors = new ArrayList<>();

        Throwable cause = ex.getCause();
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException invalidFormatException) {
            Class<?> targetType = invalidFormatException.getTargetType();
            if (targetType.isEnum()) {
                // Tratamento específico para enums
                String fieldName = invalidFormatException.getPath().stream()
                        .map(JsonMappingException.Reference::getFieldName)
                        .findFirst()
                        .orElse("desconhecido");

                String invalidValue = invalidFormatException.getValue().toString();
                List<String> validValues = Arrays.stream(targetType.getEnumConstants())
                        .map(Object::toString)
                        .toList();

                errors.add(String.format(
                        "Erro no campo '%s': o valor '%s' não é válido. Valores aceitos: %s",
                        fieldName, invalidValue, validValues
                ));
            } else {
                // Tratamento genérico para outros tipos de erros de desserialização
                errors.add("Erro de desserialização: Verifique o formato dos campos enviados.");
            }
        } else {
            // Mensagem genérica para outras causas de HttpMessageNotReadableException
            errors.add("Erro na leitura do JSON. Verifique o formato enviado.");
        }

        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGenericException(Exception ex) {
        ResponseError error = new ResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                List.of(ex.getMessage() != null ? ex.getMessage() : "Erro desconhecido.")
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}




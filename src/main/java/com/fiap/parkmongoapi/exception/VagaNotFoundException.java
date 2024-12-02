package com.fiap.parkmongoapi.exception;

public class VagaNotFoundException extends RuntimeException {
    public VagaNotFoundException(String message) {
        super(message);
    }
}

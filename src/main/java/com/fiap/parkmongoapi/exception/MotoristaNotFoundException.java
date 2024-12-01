package com.fiap.parkmongoapi.exception;

public class MotoristaNotFoundException extends RuntimeException {
    public MotoristaNotFoundException(String cpf) {
        super("Motorista não encontrado com CPF: " + cpf);
    }
}
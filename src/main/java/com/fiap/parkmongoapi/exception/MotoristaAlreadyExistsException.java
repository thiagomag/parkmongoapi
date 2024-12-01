package com.fiap.parkmongoapi.exception;

public class MotoristaAlreadyExistsException extends RuntimeException {
    public MotoristaAlreadyExistsException(String cpf) {
        super("Já existe um motorista cadastrado com o CPF: " + cpf);
    }
}

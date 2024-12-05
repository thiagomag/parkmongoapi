package com.fiap.parkmongoapi.exception.motorista;

import com.fiap.parkmongoapi.exception.ConflictException;

public class MotoristaAlreadyExistsException extends ConflictException {
    public MotoristaAlreadyExistsException(String cpf) {
        super("Já existe um motorista cadastrado com o CPF: " + cpf);
    }
}

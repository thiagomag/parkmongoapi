package com.fiap.parkmongoapi.exception.motorista;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class MotoristaNotFoundException extends EntityNotFoundException {
    public MotoristaNotFoundException(String cpf) {
        super("Motorista não encontrado com CPF: " + cpf);
    }
}
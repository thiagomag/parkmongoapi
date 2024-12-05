package com.fiap.parkmongoapi.exception.vaga;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class VagaNotFoundException extends EntityNotFoundException {
    public VagaNotFoundException(String id) {
        super("Vaga não encontrada com o Identificador: " + id);
    }
}

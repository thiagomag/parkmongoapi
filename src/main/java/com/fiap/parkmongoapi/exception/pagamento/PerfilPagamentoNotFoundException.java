package com.fiap.parkmongoapi.exception.pagamento;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class PerfilPagamentoNotFoundException extends EntityNotFoundException {

    public PerfilPagamentoNotFoundException(String id) {
        super("Perfil de pagamento não encontrado com o id: " + id);
    }
}

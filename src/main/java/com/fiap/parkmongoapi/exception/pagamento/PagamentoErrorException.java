package com.fiap.parkmongoapi.exception.pagamento;

public class PagamentoErrorException extends RuntimeException {
    public PagamentoErrorException(String message) {
        super(message);
    }
}

package com.fiap.parkmongoapi.client.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagamentoFeingClientResponse {

    private String id;
    private String cpfCliente;
    private Long perfilPagamentoId;
    private BigDecimal valorPagamento;
    private String statusPagamento;
    private String dataPagamento;
}

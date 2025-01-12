package com.fiap.parkmongoapi.client.dto;

import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoFeingClientRequest {

    private String cpfCliente;
    private Long perfilPagamentoId;
    private BigDecimal valorPagamento;

    public PagamentoFeingClientRequest(PagamentoDTO pagamentoDto) {
        this.cpfCliente = pagamentoDto.motoristaCpf();
        this.perfilPagamentoId = Long.valueOf(pagamentoDto.idPerfilPagamento());
        this.valorPagamento = pagamentoDto.valor();
    }
}

package com.fiap.parkmongoapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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

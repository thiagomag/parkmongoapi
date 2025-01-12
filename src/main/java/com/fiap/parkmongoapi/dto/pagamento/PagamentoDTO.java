package com.fiap.parkmongoapi.dto.pagamento;

import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Pagamento;
import com.fiap.parkmongoapi.model.PerfilPagamento;
import com.fiap.parkmongoapi.model.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "DTO para criação de pagamento.")
public record PagamentoDTO(
        String ticketId,
        String motoristaCpf,
        BigDecimal valor,
        String idPerfilPagamento
) {
    public Pagamento toEntity(PerfilPagamento perfilPagamento, Ticket ticket, Motorista motorista) {
        return Pagamento.builder()
                .perfilPagamento(perfilPagamento)
                .ticket(ticket)
                .statusPagamento("Pendente")
                .valorPagamento(this.valor())
                .motorista(motorista)
                .build();
    }
}
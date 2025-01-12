package com.fiap.parkmongoapi.dto.pagamento;

import com.fiap.parkmongoapi.dto.ticket.TicketViewDTO;
import com.fiap.parkmongoapi.model.Pagamento;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PagamentoResponseDTO(
        TicketViewDTO ticket,
        String cpfMotorista,
        String perfilPagamento,
        String statusPagamento,
        LocalDateTime dataPagamento
) {
    public static PagamentoResponseDTO toDTO(Pagamento pagamento) {
        final var ticket = pagamento.getTicket();
        final var motorista = ticket.getMotorista();
        final var veiculo = ticket.getVeiculo();
        final var vaga = ticket.getVaga();
        return PagamentoResponseDTO.builder()
                .ticket(TicketViewDTO.toDto(ticket, motorista, veiculo, vaga))
                .cpfMotorista(pagamento.getTicket().getMotorista().getCpf())
                .perfilPagamento(pagamento.getPerfilPagamento().getId())
                .statusPagamento(pagamento.getStatusPagamento())
                .dataPagamento(pagamento.getDataPagamento())
                .build();
    }
}

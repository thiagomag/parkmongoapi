package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO para resposta de cadastro de tickets.")
public record TicketCreatedDTO(

        @Schema(description = "ID do Ticket.", example = "64d7ce998866f675a4d40909")
        String idTicket,

        @Schema(description = "ID da vaga.", example = "a1b2c3d4-001")
        String vagaLocId,

        @Schema(description = "CPF do motorista.", example = "12345678901")
        String motoristaCpf,

        @Schema(description = "Placa do veículo.", example = "ABC1234")
        String veiculoPlaca,

        @Schema(description = "Data e hora de início do ticket.")
        LocalDateTime inicio,

        @Schema(description = "Status do ticket.", example = "EM_ABERTO")
        EnumStatusTicket status
) {
    public static TicketCreatedDTO toDto(Ticket ticket) {

        return new TicketCreatedDTO(
                ticket.getId(),
                ticket.getVaga().getLocId(),
                ticket.getMotorista().getCpf(),
                ticket.getVeiculo().getPlaca(),
                ticket.getInicio(),
                ticket.getStatus());
    }

}

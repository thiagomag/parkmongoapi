package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO para resposta de cancelamento de tickets.")
public record TicketCancelledDTO(

        @Schema(description = "ID do ticket.", example = "64d7ce998866f675a4d40909")
        String id,

        @Schema(description = "Data e hora de início do ticket.")
        LocalDateTime inicio,

        @Schema(description = "Data e hora de cancelamento do ticket.")
        LocalDateTime dataCancelamento,

        @Schema(description = "Valor do pagamento.", example = "10.50")
        BigDecimal valor,

        @Schema(description = "Status do ticket.", example = "CANCELADO")
        EnumStatusTicket status
) {
    public static TicketCancelledDTO toDto(Ticket ticket) {

        return new TicketCancelledDTO(
                ticket.getId(),
                ticket.getInicio(),
                ticket.getFim(),
                ticket.getValor(),
                ticket.getStatus()
        );
    }
}

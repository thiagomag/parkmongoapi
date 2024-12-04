package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO para resposta de pagamento de tickets.")
public record TicketPaidDTO(

        @Schema(description = "ID do ticket.", example = "64d7ce998866f675a4d40909")
        String id,

        @Schema(description = "Data e hora de início do ticket.")
        LocalDateTime inicio,

        @Schema(description = "Data e hora de fim do ticket.")
        LocalDateTime fim,

        @Schema(description = "Valor do pagamento.", example = "10.50")
        BigDecimal valor,

        @Schema(description = "Status do ticket.", example = "PAGO")
        EnumStatusTicket status
) {
    public static TicketPaidDTO toDto(Ticket ticket) {

        return new TicketPaidDTO(
                ticket.getId(),
                ticket.getInicio(),
                ticket.getFim(),
                ticket.getValor(),
                ticket.getStatus()
        );
    }
}

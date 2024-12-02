package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroTicketDTO(

        @NotBlank(message = "O ID da vaga é obrigatório.")
        String vagaId,

        @NotBlank(message = "O ID do motorista é obrigatório.")
        String motoristaId,

        @NotBlank(message = "O ID do veículo é obrigatório.")
        String veiculoId

) {}

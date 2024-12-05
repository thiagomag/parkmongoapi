package com.fiap.parkmongoapi.dto.ticket;


import com.fiap.parkmongoapi.validations.ValidCpf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Schema(description = "DTO para cadastro de tickets.")
public record CadastroTicketDTO(

        @Schema(description = "ID da vaga ou LocId.", example = "4e86954d-001")
        @NotBlank(message = "O ID da vaga é obrigatório.")
        String vagaId,

        @Schema(description = "CPF do motorista.", example = "12345678901")
        @NotBlank(message = "O CPF do motorista é obrigatório.")
        @ValidCpf
        String motoristacpf,

        @Schema(description = "ID ou Placa do veículo.", example = "LUK1333")
        @NotBlank(message = "O ID ou Placa do Veiculo do veículo é obrigatório.")
        String veiculoPlaca

) {}
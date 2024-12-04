package com.fiap.parkmongoapi.dto.ticket;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Schema(description = "DTO para cadastro de tickets.")
public record CadastroTicketDTO(

        @Schema(description = "ID da vaga.", example = "64d7ce998866f675a4d40909")
        @NotBlank(message = "O ID da vaga é obrigatório.")
        String vagaId,

        @Schema(description = "CPF do motorista.", example = "12345678901")
        @NotBlank(message = "O CPF do motorista é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
        String motoristacpf,

        @Schema(description = "ID do veículo.", example = "64d7ce998866f675a4d40907")
        @NotBlank(message = "O ID do veículo é obrigatório.")
        String veiculoPlaca

) {}
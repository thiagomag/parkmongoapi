package com.fiap.parkmongoapi.dto.motorista;

import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO para atualização de motoristas.")
public record AtualizaMotoristaDTO(

        @Schema(description = "Nome completo do motorista.", example = "João da Silva")
        String nome,

        @Schema(description = "Data de nascimento do motorista.", example = "1990-01-23")
        LocalDate dataNascimento,

        @Schema(description = "Endereço de e-mail do motorista.", example = "joao.silva@example.com")
        String email,

        @Schema(description = "Perfil do motorista.", example = "COMUM")
        EnumPerfil perfil

) { }



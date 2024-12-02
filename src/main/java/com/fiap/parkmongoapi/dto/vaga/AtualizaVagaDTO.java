package com.fiap.parkmongoapi.dto.vaga;

import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "DTO para atualizar as informações de uma vaga de estacionamento")
public record AtualizaVagaDTO(

        @Schema(description = "Perfil do veículo associado à vaga",
                example = "COMUM")
        EnumPerfil perfil,

        @Schema(description = "Tipo do veículo associado à vaga",
                example = "CARRO")
        EnumTipoVeiculo tipoVeiculo,

        @Schema(description = "Tarifa associada à vaga",
                example = "5.50")
        BigDecimal tarifa
) {}

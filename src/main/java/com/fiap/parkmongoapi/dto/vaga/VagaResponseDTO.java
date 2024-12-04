package com.fiap.parkmongoapi.dto.vaga;

import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Schema(description = "Representação de uma vaga.")
public record VagaResponseDTO(

        @Schema(description = "ID da vaga.", example = "64d7ce998866f675a4d40909")
        String id,

        @Schema(description = "ID da localização da vaga.", example = "a1b2c3d4-001") // Exemplo ajustado
        String locId,

        @Schema(description = "Perfil da vaga.", example = "COMUM")
        EnumPerfil perfil,

        @Schema(description = "Tipo de veículo permitido na vaga.", example = "CARRO")
        EnumTipoVeiculo tipoVeiculo,

        @Schema(description = "Tarifa da vaga.", example = "5.00")
        BigDecimal tarifa,

        @Schema(description = "Endereço da vaga.")
        Endereco endereco
) {
        public static   VagaResponseDTO toDto(Vaga vaga) {
                return new VagaResponseDTO(
                        vaga.getId(),
                        vaga.getLocId(),
                        vaga.getPerfil(),
                        vaga.getTipoVeiculo(),
                        vaga.getTarifa(),
                        vaga.getEndereco()
                );
        }
}
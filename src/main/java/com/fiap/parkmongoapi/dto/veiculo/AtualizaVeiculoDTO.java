package com.fiap.parkmongoapi.dto.veiculo;

import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para atualização de veículos.")
public record AtualizaVeiculoDTO(

        @Schema(description = "Placa do veículo.", example = "ABC1234", maxLength = 10)
        @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.")
        String placa,

        @Schema(description = "Modelo do veículo.", example = "Gol", maxLength = 20)
        @Size(max = 20, message = "O modelo deve ter no máximo 20 caracteres.")
        String modelo,

        @Schema(description = "Tipo de veículo.", example = "CARRO")
        EnumTipoVeiculo tipoVeiculo
) {
}

package com.fiap.parkmongoapi.dto.veiculo;

import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um veículo.")
public record VeiculoResponseDTO(

        @Schema(description = "ID do veículo.", example = "674058ff4201007a314b8204")
        String id,

        @Schema(description = "Placa do veículo.", example = "ABC1234")
        String placa,

        @Schema(description = "Modelo do veículo.", example = "Gol")
        String modelo,

        @Schema(description = "Tipo do veículo.", example = "CARRO")
        EnumTipoVeiculo tipoVeiculo,

        @Schema(description = "CPF do motorista proprietário do veículo.", example = "12345678901")
        String cpfMotorista
) {

    public static VeiculoResponseDTO toDTO(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getTipoVeiculo(),
                veiculo.getCpfMotorista());
    }
}

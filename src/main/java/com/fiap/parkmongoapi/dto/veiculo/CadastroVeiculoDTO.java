package com.fiap.parkmongoapi.dto.veiculo;

import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para cadastro de um veículo.")
public record CadastroVeiculoDTO(

        @Schema(description = "Placa do veículo.", example = "ABC1234", required = true)
        @NotBlank(message = "A placa é obrigatória.")
        String placa,

        @Schema(description = "Modelo do veículo.", example = "Gol", required = true)
        @NotBlank(message = "O modelo é obrigatório.")
        String modelo,

        @Schema(description = "Tipo do veículo.", example = "CARRO", required = true)
        @NotNull(message = "O tipo do veículo é obrigatório.")
        EnumTipoVeiculo tipoVeiculo
) {
}

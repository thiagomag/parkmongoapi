package com.fiap.parkmongoapi.dto.veiculo;

import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroVeiculoDTO(
        @NotBlank(message = "A placa é obrigatória.") String placa,
        @NotBlank(message = "O modelo é obrigatório.") String modelo,
        @NotNull(message = "O tipo do veículo é obrigatório.") EnumTipoVeiculo tipoVeiculo
) {
}

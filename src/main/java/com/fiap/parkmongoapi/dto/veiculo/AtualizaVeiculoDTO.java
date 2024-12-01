package com.fiap.parkmongoapi.dto.veiculo;

import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import jakarta.validation.constraints.Size;

public record AtualizaVeiculoDTO(
        @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.")
        String placa,
        @Size(max = 20, message = "O modelo deve ter no máximo 20 caracteres.")
        String modelo,
        EnumTipoVeiculo tipoVeiculo ) {
}

package com.fiap.parkmongoapi.dto.vaga;

import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record VagaResponseDTO(

        String id,
        String locId,
        EnumPerfil perfil,
        EnumTipoVeiculo tipoVeiculo,
        BigDecimal tarifa,
        Endereco endereco


) {
}

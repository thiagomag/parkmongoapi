package com.fiap.parkmongoapi.dto.vaga;

import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;

public record VagaFiltroDTO(
        String cep,
        String cidade,
        String uf,
        EnumPerfil perfil,
        EnumTipoVeiculo tipoVeiculo
) {
}

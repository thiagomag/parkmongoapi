package com.fiap.parkmongoapi.model;


import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import com.fiap.parkmongoapi.utils.VagaUtils;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Vaga {

    @Id
    private String id;

    private String locId;

    @NotNull
    private EnumPerfil perfil; // Enum para definir o perfil da vaga

    @NotNull
    private EnumTipoVeiculo tipoVeiculo;

    @NotNull(message = "A tarifa não pode ser nula.")
    @DecimalMin(value = "0.0", inclusive = false, message = "A tarifa deve ser maior que zero.")
    private BigDecimal tarifa;

    @NotNull
    private Endereco endereco;

    public Vaga(Endereco endereco, EnumPerfil perfil, EnumTipoVeiculo tipoVeiculo, BigDecimal tarifa) {
        this.locId = VagaUtils.gerarIdCustomizado(endereco);
        this.perfil = perfil;
        this.tipoVeiculo = tipoVeiculo;
        this.tarifa = tarifa;
        this.endereco = endereco;
    }

}

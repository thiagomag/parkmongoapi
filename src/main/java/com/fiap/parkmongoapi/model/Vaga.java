package com.fiap.parkmongoapi.model;


import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
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

    @NotNull
    private Endereco endereco;

    @NotNull
    private EnumPerfil perfil; // Enum para definir o perfil da vaga

    @NotNull
    private EnumTipoVeiculo tipoVeiculo;

    @NotNull(message = "A tarifa não pode ser nula.")
    @DecimalMin(value = "0.0", inclusive = false, message = "A tarifa deve ser maior que zero.")
    private BigDecimal tarifa;

}

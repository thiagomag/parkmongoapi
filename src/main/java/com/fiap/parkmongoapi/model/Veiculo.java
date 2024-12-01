package com.fiap.parkmongoapi.model;

import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    private String id;

    @NotBlank(message = "A placa é obrigatória.")
    private String placa;

    @NotBlank(message = "O modelo é obrigatório.")
    private String modelo;

    @NotNull(message = "O tipo do veículo é obrigatório.")
    private EnumTipoVeiculo tipoVeiculo;

    @NotBlank(message = "O CPF do motorista é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    private String cpfMotorista;

}

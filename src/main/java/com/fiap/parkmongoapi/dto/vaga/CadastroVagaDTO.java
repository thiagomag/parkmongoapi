package com.fiap.parkmongoapi.dto.vaga;

import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "DTO para cadastro de uma vaga de estacionamento")
public record CadastroVagaDTO(

        @NotNull(message = "O perfil não pode ser nulo.")
        @Schema(description = "Perfil associado à vaga",
                example = "COMUM")
        EnumPerfil perfil,

        @NotNull(message = "O tipo de veículo não pode ser nulo.")
        @Schema(description = "Tipo de veículo permitido na vaga",
                example = "CARRO")
        EnumTipoVeiculo tipoVeiculo,

        @NotNull(message = "A tarifa não pode ser nula.")
        @DecimalMin(value = "0.0", inclusive = false, message = "A tarifa deve ser maior que zero.")
        @Schema(description = "Tarifa da vaga de estacionamento",
                example = "5.50")
        BigDecimal tarifa,


        @NotBlank(message = "O CEP não pode ser vazio.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
        @Schema(description = "CEP da vaga de estacionamento",
                example = "18109-052")
                String cep,

        @NotBlank(message = "O estado (UF) não pode ser vazio.")
        @Size(min = 2, max = 2, message = "O estado (UF) deve conter 2 letras.")
        @Schema(description = "Estado (UF) da vaga",
                example = "SP")
        String uf,

        @NotBlank(message = "A cidade não pode ser vazia.")
        @Schema(description = "Cidade onde a vaga está localizada",
                example = "Sorocaba")
        String cidade,

        @NotBlank(message = "O bairro não pode ser vazio.")
        @Schema(description = "Bairro onde a vaga está localizada",
                example = "Centro")
        String bairro,

        @NotBlank(message = "O logradouro não pode ser vazio.")
        @Schema(description = "Logradouro onde a vaga está localizada",
                example = "Rua XV de Novembro")
        String logradouro

) {


    public Endereco toEndereco() {
        return new Endereco(cep, uf, cidade, bairro, logradouro);
    }

    public  Vaga toEntity(String locId) {
        Endereco endereco = toEndereco();
        return new Vaga(locId ,endereco, perfil, tipoVeiculo, tarifa);
    }

}

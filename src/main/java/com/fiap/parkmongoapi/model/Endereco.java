package com.fiap.parkmongoapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Entidade que representa um endereço.")
public record Endereco(

        @Schema(description = "CEP.", example = "01310-000")
        @NotBlank(message = "O CEP não pode ser vazio.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
        String cep,

        @Schema(description = "Estado (UF).", example = "SP")
        @NotBlank(message = "O estado (UF) não pode ser vazio.")
        @Size(min = 2, max = 2, message = "O estado (UF) deve conter 2 letras.")
        String uf,

        @Schema(description = "Cidade.", example = "São Paulo")
        @NotBlank(message = "A cidade não pode ser vazia.")
        String cidade,

        @Schema(description = "Bairro.", example = "Bela Vista")
        @NotBlank(message = "O bairro não pode ser vazio.")
        String bairro,

        @Schema(description = "Logradouro.", example = "Avenida Paulista")
        @NotBlank(message = "O logradouro não pode ser vazio.")
        String logradouro
) {}

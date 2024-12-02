package com.fiap.parkmongoapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Endereco(
        @NotBlank(message = "O CEP não pode ser vazio.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
        String cep,

        @NotBlank(message = "O estado (UF) não pode ser vazio.")
        @Size(min = 2, max = 2, message = "O estado (UF) deve conter 2 letras.")
        String uf,

        @NotBlank(message = "A cidade não pode ser vazia.")
        String cidade,

        @NotBlank(message = "O bairro não pode ser vazio.")
        String bairro,

        @NotBlank(message = "O logradouro não pode ser vazio.")
        String logradouro


) {}

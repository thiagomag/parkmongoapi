package com.fiap.parkmongoapi.dto.motorista;

import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;

public record CadastroMotoristaDTO(
        @NotBlank(message = "O CPF é obrigatório.")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
        String cpf,

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser anterior à data atual.")
        LocalDate dataNascimento,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,

        @NotNull(message = "O perfil é obrigatório.")
        EnumPerfil perfil
) {
    public Motorista toEntity() {
        Motorista motorista = new Motorista();
        motorista.setCpf(this.cpf);
        motorista.setNome(this.nome);
        motorista.setDataNascimento(this.dataNascimento);
        motorista.setEmail(this.email);
        motorista.setPerfil(this.perfil);
        motorista.setVeiculos(new ArrayList<>());
        return motorista;
    }
}

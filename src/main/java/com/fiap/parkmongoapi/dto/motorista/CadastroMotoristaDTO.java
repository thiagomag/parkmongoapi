package com.fiap.parkmongoapi.dto.motorista;

import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.validations.ValidCpf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Schema(description = "DTO para cadastro de motoristas.")
public record CadastroMotoristaDTO(

        @Schema(description = "CPF do motorista.", example = "12345678901")
        @NotBlank(message = "O CPF é obrigatório.")
        @ValidCpf
        String cpf,

        @Schema(description = "Nome completo do motorista.", example = "João da Silva")
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @Schema(description = "Data de nascimento do motorista.", example = "1990-01-23")
        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser anterior à data atual.")
        LocalDate dataNascimento,

        @Schema(description = "Endereço de e-mail do motorista.", example = "joao.silva@example.com")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,

        @Schema(description = "Perfil do motorista.", example = "COMUM")
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

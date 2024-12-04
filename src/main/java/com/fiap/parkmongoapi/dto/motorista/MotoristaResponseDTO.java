package com.fiap.parkmongoapi.dto.motorista;

import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Representação de um motorista cadastrado.")
public record MotoristaResponseDTO(
        @Schema(description = "CPF do motorista.", example = "12345678901")
        String cpf,

        @Schema(description = "Nome completo do motorista.", example = "João da Silva")
        String nome,

        @Schema(description = "Data de nascimento do motorista.", example = "1990-01-01")
        LocalDate dataNascimento,

        @Schema(description = "Endereço de email do motorista.", example = "joao.silva@example.com")
        String email,

        @Schema(description = "Perfil do motorista.", example = "COMUM")
        EnumPerfil perfil,

        @Schema(description = "Lista de placas dos veículos do motorista.", example = "[]")
        List<String> veiculos) {

    public static MotoristaResponseDTO toDTO(Motorista motorista) {
        List<String> placasVeiculos = motorista.getVeiculos().stream()
                .map(Veiculo::getPlaca).toList();

        return new MotoristaResponseDTO(
                motorista.getCpf(),
                motorista.getNome(),
                motorista.getDataNascimento(),
                motorista.getEmail(),
                motorista.getPerfil(),
                placasVeiculos);
    }
}

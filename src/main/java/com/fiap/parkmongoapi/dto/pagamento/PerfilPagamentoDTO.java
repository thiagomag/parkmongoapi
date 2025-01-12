package com.fiap.parkmongoapi.dto.pagamento;

import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.PerfilPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para cadastro de perfil de pagamento")
public record PerfilPagamentoDTO(
        @NotBlank(message = "O CPF do motorista é obrigatório.")
        String cpfMotorista,
        Boolean isActive,
        @NotBlank(message = "A bandeira do cartão é obrigatória.")
        String bandeira,
        @NotBlank(message = "O nome do titular é obrigatório.")
        String nomeTitular,
        @NotBlank(message = "Os primeiros números do cartão são obrigatórios.")
        String primeirosNumerosCartao,
        @NotBlank(message = "Os últimos números do cartão são obrigatórios.")
        String ultimosNumerosCartao,
        @NotBlank(message = "A data de expiração é obrigatória.")
        String dataExpiracao,
        @NotBlank(message = "O token do cartão é obrigatório.")
        String tokenCartao
) {
    public PerfilPagamento toEntity(Motorista motorista, String perfilPagamentoId) {
        return PerfilPagamento.builder()
                .id(perfilPagamentoId)
                .motorista(motorista)
                .isActive(this.isActive())
                .bandeira(this.bandeira())
                .nomeTitular(this.nomeTitular())
                .primeirosNumerosCartao(this.primeirosNumerosCartao())
                .ultimosNumerosCartao(this.ultimosNumerosCartao())
                .dataExpiracao(this.dataExpiracao())
                .tokenCartao(this.tokenCartao())
                .build();
    }

}

package com.fiap.parkmongoapi.dto.pagamento;

import com.fiap.parkmongoapi.model.PerfilPagamento;

public record PerfilPagamentoResponseDTO(
        String id,
        String motoristaCpf,
        Boolean isActive
) {
    public static PerfilPagamentoResponseDTO toDto(PerfilPagamento perfilPagamento) {
        return new PerfilPagamentoResponseDTO(
                perfilPagamento.getId(),
                perfilPagamento.getMotorista().getCpf(),
                perfilPagamento.getIsActive()
        );
    }
}

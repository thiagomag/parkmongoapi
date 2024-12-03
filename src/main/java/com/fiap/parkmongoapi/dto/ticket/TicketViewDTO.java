package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketViewDTO(
        String ticketId,
        LocalDateTime inicio,
        LocalDateTime fim,
        EnumStatusTicket status,
        BigDecimal valor,

        String motoristaCpf,
        String motoristaNome,

        String veiculoPlaca,
        String veiculoModelo,
        EnumTipoVeiculo veiculoTipo,

        String vagaLocId,
        EnumTipoVeiculo vagaTipoVeiculo,
        Endereco enderecoVaga) {
}

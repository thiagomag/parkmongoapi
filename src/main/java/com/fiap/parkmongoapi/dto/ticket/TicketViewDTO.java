package com.fiap.parkmongoapi.dto.ticket;

import com.fiap.parkmongoapi.model.*;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO para visualização de tickets.")
public record TicketViewDTO(
        @Schema(description = "ID do ticket.", example = "64d7ce998866f675a4d40909")
        String ticketId,

        @Schema(description = "Data e hora de início do ticket.")
        LocalDateTime inicio,

        @Schema(description = "Data e hora de fim do ticket.")
        LocalDateTime fim,

        @Schema(description = "Status do ticket.", example = "FECHADO")
        EnumStatusTicket status,

        @Schema(description = "Valor do ticket.", example = "10.50")
        BigDecimal valor,

        @Schema(description = "Dados do motorista.")
        MotoristaViewDTO motorista,

        @Schema(description = "Dados do veículo.")
        VeiculoViewDTO veiculo,

        @Schema(description = "Dados da vaga.")
        VagaViewDTO vaga) {

    @Schema(description = "DTO para visualização de motoristas.")
    record MotoristaViewDTO(

            @Schema(description = "CPF do motorista.", example = "12345678901")
            String cpf,

            @Schema(description = "Nome do motorista.", example = "João da Silva")
            String nome
    ) {
        public static MotoristaViewDTO toDto(Motorista motorista){
            return  new MotoristaViewDTO(motorista.getCpf(), motorista.getNome());
        }
    }

    @Schema(description = "DTO para visualização de veículos.")
    record VeiculoViewDTO(

            @Schema(description = "Placa do veículo.", example = "ABC1234")
            String placa,

            @Schema(description = "Modelo do veículo.", example = "Gol")
            String modelo,

            @Schema(description = "Tipo do veículo.", example = "CARRO")
            EnumTipoVeiculo tipo
    ) {
        public static VeiculoViewDTO toDto(Veiculo veiculo){
            return  new VeiculoViewDTO(veiculo.getPlaca(), veiculo.getModelo(), veiculo.getTipoVeiculo());
        }
    }

    @Schema(description = "DTO para visualização de vagas.")
    record VagaViewDTO(

            @Schema(description = "LocID da vaga.", example = "a1b2c3d4-001")
            String locId,

            @Schema(description = "Tipo do veículo da vaga.", example = "CARRO")
            EnumTipoVeiculo tipoVeiculo,

            @Schema(description = "Endereço da vaga.")
            Endereco endereco
    ) {
        public static VagaViewDTO toDto(Vaga vaga){
            return  new VagaViewDTO(vaga.getLocId(), vaga.getTipoVeiculo(), vaga.getEndereco());
        }
    }

    public static TicketViewDTO toDto(Ticket ticket, Motorista motorista,
                                      Veiculo veiculo, Vaga vaga) {
        return new TicketViewDTO(
                ticket.getId(),
                ticket.getInicio(),
                ticket.getFim(),
                ticket.getStatus(),
                ticket.getValor(),
                MotoristaViewDTO.toDto(motorista),
                VeiculoViewDTO.toDto(veiculo),
                VagaViewDTO.toDto(vaga));

    }





}

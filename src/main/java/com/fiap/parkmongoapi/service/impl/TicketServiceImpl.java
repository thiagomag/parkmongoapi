package com.fiap.parkmongoapi.service.impl;

import com.fiap.parkmongoapi.dto.ticket.*;
import com.fiap.parkmongoapi.exception.motorista.MotoristaNotFoundException;
import com.fiap.parkmongoapi.exception.ticket.TicketAlreadyCancelledException;
import com.fiap.parkmongoapi.exception.ticket.TicketAlreadyPaidException;
import com.fiap.parkmongoapi.exception.ticket.TicketNotFoundException;
import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import com.fiap.parkmongoapi.repository.MotoristaRepository;
import com.fiap.parkmongoapi.repository.TicketRepository;
import com.fiap.parkmongoapi.repository.VagaRepository;
import com.fiap.parkmongoapi.repository.VeiculoRepository;
import com.fiap.parkmongoapi.service.TicketService;
import com.fiap.parkmongoapi.utils.TicketUtils;
import com.fiap.parkmongoapi.utils.VagaUtils;
import com.fiap.parkmongoapi.utils.VeiculoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public TicketCreatedDTO cadastrarTicket(CadastroTicketDTO cadastroTicketDTO) {

        Vaga vaga = VagaUtils.buscarVagaPorIdentificador(cadastroTicketDTO.vagaId(), vagaRepository);

        Motorista motorista = motoristaRepository.findById(cadastroTicketDTO.motoristacpf())
                .orElseThrow(() -> new MotoristaNotFoundException(cadastroTicketDTO.motoristacpf()));

        Veiculo veiculo = VeiculoUtils.buscarVeiculoPorIdOuPlaca(cadastroTicketDTO.veiculoPlaca(),
                cadastroTicketDTO.motoristacpf(), veiculoRepository);

        // Converte a hora atual para o fuso horário de São Paulo
        ZonedDateTime saoPauloTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        LocalDateTime inicio = saoPauloTime.toLocalDateTime();

        // Cria o novo ticket com base no DTO
        Ticket ticket = new Ticket();
        ticket.setVaga(vaga);
        ticket.setMotorista(motorista);
        ticket.setVeiculo(veiculo);
        ticket.setInicio(inicio);
        ticket.setStatus(EnumStatusTicket.EM_ABERTO);

        // Salva o ticket no banco de dados
        ticketRepository.save(ticket);

        return TicketCreatedDTO.toDto(ticket);
    }

    @Override
    public TicketPaidDTO pagarTicket(String ticketId) {
        // Encontra o ticket pelo ID
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        // Verifica se o ticket já foi pago
        if (ticket.getStatus() == EnumStatusTicket.PAGO) {
            throw new TicketAlreadyPaidException(ticketId ,ticket.getFim());
        }

        // Verifica se o ticket já foi Cancelado
        if (ticket.getStatus() == EnumStatusTicket.CANCELADO) {
            throw new TicketAlreadyCancelledException(ticketId, ticket.getFim());
        }

        // Obtém a hora de início e a hora atual
        LocalDateTime inicio = ticket.getInicio();
        LocalDateTime agora = LocalDateTime.now();

        // Calcula a diferença em horas entre o início e o momento atual
        Duration duration = Duration.between(inicio, agora);
        BigDecimal valorPagamento = TicketUtils.getValorPagamento(duration, ticket);

        // Atualiza o ticket com a hora de fechamento, o valor calculado e a data do pagamento
        ticket.setFim(agora);
        ticket.setStatus(EnumStatusTicket.PAGO);
        ticket.setValor(valorPagamento);

        // Salva o ticket atualizado no banco de dados
         ticketRepository.save(ticket);

         return TicketPaidDTO.toDto(ticket);
    }


    @Override
    public TicketCancelledDTO cancelaTicket(String ticketId) {

        // Encontra o ticket pelo ID
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        // Verifica se o ticket já foi pago
        if (ticket.getStatus() != EnumStatusTicket.EM_ABERTO) {
            throw new TicketAlreadyPaidException( ticketId, ticket.getFim());
        }

        LocalDateTime agora = LocalDateTime.now();

        BigDecimal valorPagamento = new BigDecimal("0.00");

        // Atualiza o ticket com a hora de fechamento, o valor calculado e a data do pagamento
        ticket.setFim(agora);
        ticket.setStatus(EnumStatusTicket.CANCELADO);
        ticket.setValor(valorPagamento);

        // Salva o ticket atualizado no banco de dados
        ticketRepository.save(ticket);

        return TicketCancelledDTO.toDto(ticket);
    }


    public TicketViewDTO findTicketById(String ticketId) {
        // Primeiro, encontramos o ticket pelo ID
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        // Buscando os dados relacionados (Motorista, Veículo, Vaga)
        Motorista motorista = ticket.getMotorista();
        Veiculo veiculo = ticket.getVeiculo();
        Vaga vaga = ticket.getVaga();


        // Mapear as informações para o DTO
        return TicketViewDTO.toDto(ticket,motorista,veiculo,vaga);


    }
}




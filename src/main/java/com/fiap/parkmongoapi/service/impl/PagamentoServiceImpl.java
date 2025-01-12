package com.fiap.parkmongoapi.service.impl;

import com.fiap.parkmongoapi.client.PagamentoFeignClient;
import com.fiap.parkmongoapi.client.dto.PagamentoFeingClientRequest;
import com.fiap.parkmongoapi.client.dto.PerfilPagamentoFeignClientRequest;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoResponseDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoResponseDTO;
import com.fiap.parkmongoapi.exception.motorista.MotoristaNotFoundException;
import com.fiap.parkmongoapi.exception.pagamento.PagamentoErrorException;
import com.fiap.parkmongoapi.exception.pagamento.PerfilPagamentoNotFoundException;
import com.fiap.parkmongoapi.exception.ticket.TicketNotFoundException;
import com.fiap.parkmongoapi.model.Pagamento;
import com.fiap.parkmongoapi.repository.MotoristaRepository;
import com.fiap.parkmongoapi.repository.PagamentoRepository;
import com.fiap.parkmongoapi.repository.PerfilPagamentoRepository;
import com.fiap.parkmongoapi.repository.TicketRepository;
import com.fiap.parkmongoapi.service.PagamentoService;
import com.fiap.parkmongoapi.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final MotoristaRepository motoristaRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;
    private final PerfilPagamentoRepository perfilPagamentoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoFeignClient pagamentoFeignClient;

    @Override
    public PagamentoResponseDTO realizarPagamento(PagamentoDTO pagamentoDTO) {
        final var pagamento = buildPagamento(pagamentoDTO);
        final var pagamentoFeignClientRequest = new PagamentoFeingClientRequest(pagamentoDTO);
        final var pagamentoFeingClientResponse = pagamentoFeignClient.realizarPagamento(pagamentoFeignClientRequest);
        if (pagamentoFeingClientResponse != null && "aprovado".equalsIgnoreCase(pagamentoFeingClientResponse.getStatusPagamento())) {
            final var pagamentoResponse = pagamentoRepository.save(pagamento);
            ticketService.pagarTicket(pagamentoDTO.ticketId());
            return PagamentoResponseDTO.toDTO(pagamentoResponse);
        }
        throw new PagamentoErrorException("Erro ao realizar o pagamento.");
    }

    private Pagamento buildPagamento(PagamentoDTO pagamentoDTO) {
        final var perfilPagamento = perfilPagamentoRepository.findById(pagamentoDTO.idPerfilPagamento())
                .orElseThrow(() -> new PerfilPagamentoNotFoundException(pagamentoDTO.idPerfilPagamento()));
        final var ticket = ticketRepository.findById(pagamentoDTO.ticketId())
                .orElseThrow(() -> new TicketNotFoundException(pagamentoDTO.ticketId()));
        final var motorista = motoristaRepository.findById(pagamentoDTO.motoristaCpf())
                .orElseThrow(() -> new MotoristaNotFoundException(pagamentoDTO.motoristaCpf()));
        return pagamentoDTO.toEntity(perfilPagamento, ticket, motorista);
    }

    @Override
    public PerfilPagamentoResponseDTO cadastrarPerfilPagamento(PerfilPagamentoDTO perfilPagamentoDTO) {
        final var perfilPagamentoFeingClientRequest = new PerfilPagamentoFeignClientRequest(perfilPagamentoDTO);
        final var perfilPagamentoId = pagamentoFeignClient.cadastrarPerfilPagamento(perfilPagamentoFeingClientRequest).getId();
        final var motorista = motoristaRepository.findById(perfilPagamentoDTO.cpfMotorista())
                .orElseThrow(() -> new MotoristaNotFoundException(perfilPagamentoDTO.cpfMotorista()));
        final var perfilPagamentoResponseDTO = perfilPagamentoDTO.toEntity(motorista, perfilPagamentoId);
        final var perfilPagamento = perfilPagamentoRepository.save(perfilPagamentoResponseDTO);
        return PerfilPagamentoResponseDTO.toDto(perfilPagamento);
    }

    @Override
    public ResponseEntity<Void> inativarPerfilPagamento(String idPerfilPagamento) {
        final var perfilPagamento = perfilPagamentoRepository.findById(idPerfilPagamento)
                .orElseThrow(() -> new PerfilPagamentoNotFoundException(idPerfilPagamento));
        perfilPagamento.setIsActive(Boolean.FALSE);
        perfilPagamentoRepository.save(perfilPagamento);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

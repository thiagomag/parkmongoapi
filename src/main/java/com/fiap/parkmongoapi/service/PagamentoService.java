package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoResponseDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PagamentoService {

    PagamentoResponseDTO realizarPagamento(PagamentoDTO pagamentoDTO);

    PagamentoResponseDTO consultarPagamento(String idPagamento);

    List<PagamentoResponseDTO> listarPagamentos();

    ResponseEntity<Void> inativarPagamento(String idPagamento);

    PerfilPagamentoResponseDTO cadastrarPerfilPagamento(PerfilPagamentoDTO pagamento);

    PerfilPagamentoResponseDTO consultarPerfilPagamento(String idPerfilPagamento);

    List<PerfilPagamentoResponseDTO> listarPerfisPagamento();

    ResponseEntity<Void> inativarPerfilPagamento(String idPerfilPagamento);
}

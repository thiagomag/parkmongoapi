package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoResponseDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PagamentoService {

    PagamentoResponseDTO realizarPagamento(PagamentoDTO pagamentoDTO);

    PerfilPagamentoResponseDTO cadastrarPerfilPagamento(PerfilPagamentoDTO pagamento);

    ResponseEntity<Void> inativarPerfilPagamento(String idPerfilPagamento);
}

package com.fiap.parkmongoapi.controller;

import com.fiap.parkmongoapi.dto.pagamento.PagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PagamentoResponseDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoDTO;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoResponseDTO;
import com.fiap.parkmongoapi.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pagamento")
@RequiredArgsConstructor
@Validated
@Tag(name = "Pagamento", description = "API para gerenciar os pagamentos dos tickets de estacionamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> realizarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(pagamentoService.realizarPagamento(pagamentoDTO));
    }

    @PostMapping("/cadastrarPerfilPagamento")
    @Operation(summary = "Cadastrar um novo Perfil de Pagamento.",
            description = "Cria um novo perfil de pagamento com as informações fornecidas.")
    public ResponseEntity<PerfilPagamentoResponseDTO> cadastrarPerfilPagamento(@Valid @RequestBody PerfilPagamentoDTO pagamento) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pagamentoService.cadastrarPerfilPagamento(pagamento));
    }

    @DeleteMapping("/inativarPerfilPagamento/{idPerfilPagamento}")
    @Operation(summary = "Inativar um Perfil de Pagamento.",
            description = "Inativar um perfil de pagamento através do id do perfil.")
    public ResponseEntity<Void> inativarPerfilPagamento(@PathVariable String idPerfilPagamento) {
        return pagamentoService.inativarPerfilPagamento(idPerfilPagamento);
    }
}

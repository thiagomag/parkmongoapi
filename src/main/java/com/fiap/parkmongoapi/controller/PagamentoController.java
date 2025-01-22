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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pagamento")
@RequiredArgsConstructor
@Validated
@Tag(name = "Pagamento", description = "API para gerenciar os pagamentos dos tickets de estacionamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Realizar um Pagamento.",
            description = "Realiza um pagamento com as informações fornecidas.")
    public ResponseEntity<PagamentoResponseDTO> realizarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(pagamentoService.realizarPagamento(pagamentoDTO));
    }

    @GetMapping("/consultarPagamento/{idPagamento}")
    @Operation(summary = "Consultar um Pagamento.",
            description = "Consulta um pagamento através do id do pagamento.")
    public ResponseEntity<PagamentoResponseDTO> consultarPagamento(@PathVariable String idPagamento) {
        return ResponseEntity.ok(pagamentoService.consultarPagamento(idPagamento));
    }

    @GetMapping("/consultarPagamento")
    @Operation(summary = "Consultar todos os Pagamentos.",
            description = "Consulta todos os pagamentos realizados.")
    public ResponseEntity<Iterable<PagamentoResponseDTO>> consultarPagamento() {
        return ResponseEntity.ok(pagamentoService.listarPagamentos());
    }

    @DeleteMapping("/inativarPagamento/{idPagamento}")
    @Operation(summary = "Inativar um Pagamento.",
            description = "Inativar um pagamento através do id do pagamento.")
    public ResponseEntity<Void> inativarPagamento(@PathVariable String idPagamento) {
        return pagamentoService.inativarPagamento(idPagamento);
    }

    @PostMapping("/cadastrarPerfilPagamento")
    @Operation(summary = "Cadastrar um novo Perfil de Pagamento.",
            description = "Cria um novo perfil de pagamento com as informações fornecidas.")
    public ResponseEntity<PerfilPagamentoResponseDTO> cadastrarPerfilPagamento(@Valid @RequestBody PerfilPagamentoDTO pagamento) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pagamentoService.cadastrarPerfilPagamento(pagamento));
    }

    @GetMapping("/consultarPerfilPagamento/{idPerfilPagamento}")
    @Operation(summary = "Consultar um Perfil de Pagamento.",
            description = "Consulta um perfil de pagamento através do id do perfil.")
    public ResponseEntity<PerfilPagamentoResponseDTO> consultarPerfilPagamento(@PathVariable String idPerfilPagamento) {
        return ResponseEntity.ok(pagamentoService.consultarPerfilPagamento(idPerfilPagamento));
    }

    @GetMapping("/consultarPerfilPagamento")
    @Operation(summary = "Consultar todos os Perfis de Pagamento.",
            description = "Consulta todos os perfis de pagamento cadastrados.")
    public ResponseEntity<List<PerfilPagamentoResponseDTO>> consultarPerfilPagamento() {
        return ResponseEntity.ok(pagamentoService.listarPerfisPagamento());
    }

    @DeleteMapping("/inativarPerfilPagamento/{idPerfilPagamento}")
    @Operation(summary = "Inativar um Perfil de Pagamento.",
            description = "Inativar um perfil de pagamento através do id do perfil.")
    public ResponseEntity<Void> inativarPerfilPagamento(@PathVariable String idPerfilPagamento) {
        return pagamentoService.inativarPerfilPagamento(idPerfilPagamento);
    }
}

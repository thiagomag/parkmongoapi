package com.fiap.parkmongoapi.controller;


import com.fiap.parkmongoapi.dto.ticket.*;
import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/ticket")
@Tag(name = "Ticket", description = "API para gerenciar park tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo ticket",
            description = "Cria um novo ticket com as informações fornecidas.")
    public ResponseEntity<TicketCreatedDTO> cadastrarTicket(
          @Valid CadastroTicketDTO cadastroTicketDTO) {

        var ticket = ticketService.cadastrarTicket(cadastroTicketDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ticket.idTicket())
                .toUri();

        return ResponseEntity.created(location).body(ticket);
    }

        @PutMapping("/paga/{ticketId}")
        @Operation(summary = "Pagar um ticket", description = "Realiza o pagamento de um ticket existente.")
        public ResponseEntity<TicketPaidDTO> pagarTicket (@PathVariable String ticketId) {

        var ticketFechamento = ticketService.pagarTicket(ticketId);

        return ResponseEntity.ok(ticketFechamento);
    }

        @PutMapping("/cancela/{ticketId}")
        @Operation(summary = "Cancelar um ticket", description = "Realiza o cancelamento de um ticket existente.")
        public ResponseEntity<TicketCancelledDTO> cancelarTicket (@PathVariable String ticketId) {

            var ticketCancelamento = ticketService.cancelaTicket(ticketId);

            return ResponseEntity.ok(ticketCancelamento);
        }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar um ticket por ID", description = "Retorna as informações detalhadas de um ticket.")
    public ResponseEntity<TicketViewDTO> findTicketById(@PathVariable String id) {
        var ticketDetalhe = ticketService.findTicketById(id);
        return ResponseEntity.ok(ticketDetalhe);
    }




}



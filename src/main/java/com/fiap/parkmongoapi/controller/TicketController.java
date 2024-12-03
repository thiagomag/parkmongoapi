package com.fiap.parkmongoapi.controller;


import com.fiap.parkmongoapi.dto.ticket.CadastroTicketDTO;
import com.fiap.parkmongoapi.dto.ticket.TicketViewDTO;
import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.service.TicketService;
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
    public ResponseEntity<Ticket> cadastrarTicket(
          @Valid CadastroTicketDTO cadastroTicketDTO) {

        var ticket = ticketService.cadastrarTicket(cadastroTicketDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();

        return ResponseEntity.created(location).body(ticket);
    }

        @PutMapping("/{id}")
        public ResponseEntity<Ticket> pagarTicket (@PathVariable String ticketId) {

        var ticketFechamento = ticketService.pagarTicket(ticketId);

        return ResponseEntity.ok(ticketFechamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketViewDTO> findTicketById(@PathVariable String id) {
        var ticketDetalhe = ticketService.findTicketById(id);
        return ResponseEntity.ok(ticketDetalhe);
    }




}



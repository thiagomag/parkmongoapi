package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.ticket.*;


public interface TicketService {

    TicketCreatedDTO cadastrarTicket(CadastroTicketDTO cadastroTicketDTO);

    void pagarTicket(String ticketId);

    TicketCancelledDTO cancelaTicket(String ticketId);

    TicketViewDTO findTicketById(String ticketId);
}

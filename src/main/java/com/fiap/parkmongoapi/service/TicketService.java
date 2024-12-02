package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.dto.ticket.CadastroTicketDTO;


public interface TicketService {

    public Ticket cadastrarTicket(CadastroTicketDTO cadastroTicketDTO);

    public Ticket pagarTicket(String ticketId);





}

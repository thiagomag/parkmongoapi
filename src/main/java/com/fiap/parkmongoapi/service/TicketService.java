package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.ticket.*;
import com.fiap.parkmongoapi.model.Ticket;


public interface TicketService {

    public TicketCreatedDTO cadastrarTicket(CadastroTicketDTO cadastroTicketDTO);

    public TicketPaidDTO pagarTicket(String ticketId);

    public TicketCancelledDTO cancelaTicket (String ticketId);

    public TicketViewDTO findTicketById(String ticketId);





}

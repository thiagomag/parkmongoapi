package com.fiap.parkmongoapi.exception.ticket;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class TicketNotFoundException extends EntityNotFoundException {

    public TicketNotFoundException(String ticketId) {
        super("Ticket não encontrado com ID: " + ticketId);
    }
}

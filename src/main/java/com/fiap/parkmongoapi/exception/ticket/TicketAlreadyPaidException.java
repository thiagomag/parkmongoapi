package com.fiap.parkmongoapi.exception.ticket;

import java.time.LocalDateTime;

public class TicketAlreadyPaidException extends RuntimeException{
    public TicketAlreadyPaidException(String id, LocalDateTime dataFim) {

        super("Ticket com id: " + id + ", já foi pago na data e horário: " + dataFim);
    }
}

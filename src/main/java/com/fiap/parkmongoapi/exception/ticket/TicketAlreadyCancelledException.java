package com.fiap.parkmongoapi.exception.ticket;

import com.fiap.parkmongoapi.exception.ConflictException;

import java.time.LocalDateTime;

public class TicketAlreadyCancelledException extends ConflictException {
  public TicketAlreadyCancelledException(String id, LocalDateTime dataFim) {

    super("Ticket com id: " + id + ", já foi cancelado na data e horário: " + dataFim);
  }
}

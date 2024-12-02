package com.fiap.parkmongoapi.exception;

public class TicketAlreadyPaidException extends RuntimeException{
    public TicketAlreadyPaidException(String message) {
        super(message);
    }
}

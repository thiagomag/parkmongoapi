package com.fiap.parkmongoapi.exception;

public class TicketNotFoundException extends  RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}

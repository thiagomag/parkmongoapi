package com.fiap.parkmongoapi.model.enums;

public enum EnumStatusTicket {
    EM_ABERTO,   // Ticket ativo, ainda não finalizado
    PAGO,        // Ticket finalizado e quitado
    INADIMPLENTE, // Ticket vencido e não pago
    CANCELADO    // Ticket cancelado antes do encerramento
}

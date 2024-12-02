package com.fiap.parkmongoapi.utils;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.model.Vaga;

import java.math.BigDecimal;
import java.time.Duration;

public class TicketUtils {

    public static BigDecimal getValorPagamento(Duration duration, Ticket ticket) {

        // Calcula as horas, arredondando para cima caso haja uma fração de hora
        long horas = (long) Math.ceil(duration.toMinutes() / 60.0);

        // Recupera a vaga associada ao ticket e sua tarifa
        Vaga vaga = ticket.getVaga();
        BigDecimal tarifa = vaga.getTarifa();

        // Calcula o valor a ser pago
        return tarifa.multiply(BigDecimal.valueOf(horas));
    }





}

package com.fiap.parkmongoapi.service.impl;

import com.fiap.parkmongoapi.model.Ticket;
import com.fiap.parkmongoapi.repository.MotoristaRepository;
import com.fiap.parkmongoapi.repository.TicketRepository;
import com.fiap.parkmongoapi.service.NotiicacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotiicacaoServiceImpl implements NotiicacaoService {

    private final TicketRepository ticketRepository;
    private final MotoristaRepository motoristaRepository;
    private final JavaMailSender mailSender;

    @Override
    public void verificarNotificacoes() {
        List<Ticket> tickets = ticketRepository.findAll();

        for (Ticket ticket : tickets) {
            final var agora = LocalDateTime.now();
            final var horarioFim = ticket.getFim();
            final var motorista = motoristaRepository.findById(ticket.getMotorista().getCpf())
                    .orElseThrow(() -> {
                        final var msg = "Motorista não encontrado";
                        log.error(msg);
                        return new RuntimeException(msg);
                    });
            if (horarioFim.minusMinutes(5).isBefore(agora) && !ticket.isNotificadoPreVencimento()) {
                this.enviarNotificacao(
                        motorista.getEmail(),
                        "Seu tempo de ticket está acabando. Renove ou saia em 5 minutos."
                );
                ticket.setNotificadoPreVencimento(true);
                ticketRepository.save(ticket);
            }

            if (horarioFim.isBefore(agora) && !ticket.isNotificadoFim()) {
                this.enviarNotificacao(
                        motorista.getEmail(),
                        "Seu tempo de ticket acabou. Por favor, regularize sua situação."
                );
                ticket.setNotificadoFim(true);
                ticketRepository.save(ticket);
            }
        }
    }

    @Override
    public void enviarNotificacao(String email, String mensagem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Notificação de Estacionamento");
        message.setText(mensagem);
        mailSender.send(message);
        log.info("Notificação enviada para " + email);
    }
}

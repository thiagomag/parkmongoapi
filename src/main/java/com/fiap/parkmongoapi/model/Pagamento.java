package com.fiap.parkmongoapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Pagamento {

    @Id
    private String id;

    @DBRef
    @NotBlank(message = "O ID da ticket é obrigatório.")
    private Ticket ticket;

    @DBRef
    @NotBlank(message = "O ID do perfil de pagamento é obrigatório.")
    private PerfilPagamento perfilPagamento;

    @DBRef
    @NotBlank(message = "O ID do motorista é obrigatório.")
    private Motorista motorista;

    @NotNull(message = "O valor do pagamento não pode ser nulo.")
    private BigDecimal valorPagamento;

    @NotNull(message = "O status não pode ser nulo.")
    private String statusPagamento;

    @NotNull(message = "A data de pagamento não pode ser nulo.")
    private LocalDateTime dataPagamento;
}

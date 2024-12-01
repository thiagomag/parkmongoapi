package com.fiap.parkmongoapi.model;

import com.fiap.parkmongoapi.model.enums.EnumStatusTicket;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
public class Ticket {

    @Id
    private String id;

    @DBRef
    @NotBlank(message = "O ID da vaga é obrigatório.")
    private Vaga vaga;

    @DBRef
    @NotBlank(message = "O ID do motorista é obrigatório.")
    private Motorista motorista;

    @DBRef
    @NotBlank(message = "O ID do veículo é obrigatório.")
    private Veiculo veiculo; // Referência ao veículo utilizado no ticket

    @NotNull(message = "A data de início não pode ser nula.")
    private LocalDateTime inicio;

    private LocalDateTime fim;

    @NotNull(message = "O status não pode ser nulo.")
    private EnumStatusTicket status;


}

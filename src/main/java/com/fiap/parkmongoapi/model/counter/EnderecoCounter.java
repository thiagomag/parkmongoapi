package com.fiap.parkmongoapi.model.counter;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class EnderecoCounter {

    @Id
    private String id; // Usado para armazenar o hash do endereço

    private int sequence; // Próximo valor do incremento
}

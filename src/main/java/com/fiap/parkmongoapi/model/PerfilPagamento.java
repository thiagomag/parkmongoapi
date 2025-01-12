package com.fiap.parkmongoapi.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilPagamento {

    private String id;
    @DBRef
    private Motorista motorista;
    private Boolean isActive;
    private String bandeira;
    private String nomeTitular;
    private String primeirosNumerosCartao;
    private String ultimosNumerosCartao;
    private String dataExpiracao;
    private String tokenCartao;

}

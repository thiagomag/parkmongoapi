package com.fiap.parkmongoapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiap.parkmongoapi.dto.pagamento.PerfilPagamentoDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PerfilPagamentoFeignClientRequest {

    String cpfMotorista;
    Boolean isActive;
    String bandeira;
    String nomeTitular;
    String primeirosNumerosCartao;
    String ultimosNumerosCartao;
    String dataExpiracao;
    String tokenCartao;

    public PerfilPagamentoFeignClientRequest(PerfilPagamentoDTO perfilPagamentoDTO) {
        this.cpfMotorista = perfilPagamentoDTO.cpfMotorista();
        this.isActive = perfilPagamentoDTO.isActive();
        this.bandeira = perfilPagamentoDTO.bandeira();
        this.nomeTitular = perfilPagamentoDTO.nomeTitular();
        this.primeirosNumerosCartao = perfilPagamentoDTO.primeirosNumerosCartao();
        this.ultimosNumerosCartao = perfilPagamentoDTO.ultimosNumerosCartao();
        this.dataExpiracao = perfilPagamentoDTO.dataExpiracao();
        this.tokenCartao = perfilPagamentoDTO.tokenCartao();
    }

}

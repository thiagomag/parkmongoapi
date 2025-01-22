package com.fiap.parkmongoapi.client;

import com.fiap.parkmongoapi.client.dto.PagamentoFeingClientRequest;
import com.fiap.parkmongoapi.client.dto.PagamentoFeingClientResponse;
import com.fiap.parkmongoapi.client.dto.PerfilPagamentoFeignClientRequest;
import com.fiap.parkmongoapi.client.dto.PerfilPagamentoFeignClientResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-pagamento", url = "http://localhost:8081")
public interface PagamentoFeignClient {


    @PostMapping("/pagamento")
    PagamentoFeingClientResponse realizarPagamento(@RequestBody PagamentoFeingClientRequest pagamentoFeingClientRequest);

    @PostMapping("/cadastrarPerfilPagamento")
    PerfilPagamentoFeignClientResponse cadastrarPerfilPagamento(@RequestBody PerfilPagamentoFeignClientRequest perfilPagamentoFeignClientRequest);

}

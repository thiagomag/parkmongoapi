package com.fiap.parkmongoapi.client;

import com.fiap.parkmongoapi.client.dto.PagamentoFeingClientRequest;
import com.fiap.parkmongoapi.client.dto.PagamentoFeingClientResponse;
import com.fiap.parkmongoapi.client.dto.PerfilPagamentoFeignClientRequest;
import com.fiap.parkmongoapi.client.dto.PerfilPagamentoFeignClientResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Api Pagamento", url = "http://localhost:8080")
public interface PagamentoFeignClient {


    @PostMapping("/pagamento")
    PagamentoFeingClientResponse realizarPagamento(@RequestBody PagamentoFeingClientRequest pagamentoFeingClientRequest);

    @PostMapping("/cadastrarPerfilPagamento")
    PerfilPagamentoFeignClientResponse cadastrarPerfilPagamento(@RequestBody PerfilPagamentoFeignClientRequest perfilPagamentoFeignClientRequest);

}

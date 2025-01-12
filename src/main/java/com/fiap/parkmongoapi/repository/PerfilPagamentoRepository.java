package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.PerfilPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilPagamentoRepository extends MongoRepository<PerfilPagamento,String> {
}

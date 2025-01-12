package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagamentoRepository extends MongoRepository<Pagamento, String> {
}

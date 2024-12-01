package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Motorista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends MongoRepository<Motorista,String> {
}

package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Vaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends MongoRepository<Vaga,String> {
}

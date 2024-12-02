package com.fiap.parkmongoapi.repository.counterRepository;

import com.fiap.parkmongoapi.model.counter.EnderecoCounter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoCounterRepository extends MongoRepository<EnderecoCounter, String> {

}
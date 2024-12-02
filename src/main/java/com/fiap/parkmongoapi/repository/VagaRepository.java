package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.dto.vaga.VagaFiltroDTO;
import com.fiap.parkmongoapi.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VagaRepository extends MongoRepository<Vaga,String> {

    Optional<Vaga> findByLocId(String locId);

}


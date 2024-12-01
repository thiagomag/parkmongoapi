package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VeiculoRepository extends MongoRepository<Veiculo,String> {

    public Optional<Veiculo> findByPlaca(String placa);

    Optional<Veiculo> findByPlacaAndCpfMotorista(String placa, String cpfMotorista);



}

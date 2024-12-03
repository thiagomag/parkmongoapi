package com.fiap.parkmongoapi.repository;

import com.fiap.parkmongoapi.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends MongoRepository<Veiculo,String> {

    public Optional<Veiculo> findByPlaca(String placa);

    public Optional<Veiculo> findByPlacaAndCpfMotorista(String placa, String cpfMotorista);

    public Optional<Page<Veiculo>> findByCpfMotorista(String cpfMotorista, Pageable pageable);

    public List<Veiculo> findByCpfMotorista(String cpfMotorista);

   public void deleteByCpfMotorista(String cpfMotorista);




}

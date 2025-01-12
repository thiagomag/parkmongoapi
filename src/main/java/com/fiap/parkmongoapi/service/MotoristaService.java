package com.fiap.parkmongoapi.service;


import com.fiap.parkmongoapi.model.Motorista;
import org.springframework.stereotype.Service;


@Service
public interface MotoristaService {

    // Consultar um motorista pelo CPF
    Motorista consultarMotoristaPorCpf(String cpf);

    // Cadastrar um motorista
    Motorista cadastrarMotorista(Motorista motorista);

    // Atualizar dados de um motorista
    Motorista atualizarMotorista(String cpf, Motorista motoristaAtualizado);

    // Deletar um motorista
    void deletarMotorista(String cpf);

}







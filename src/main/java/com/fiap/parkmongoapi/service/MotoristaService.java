package com.fiap.parkmongoapi.service;


import com.fiap.parkmongoapi.model.Motorista;
import org.springframework.stereotype.Service;


@Service
public interface MotoristaService {

    // Consultar um motorista pelo CPF
    public Motorista consultarMotoristaPorCpf(String cpf);

    // Cadastrar um motorista
    public Motorista cadastrarMotorista(Motorista motorista);

    // Atualizar dados de um motorista
    public Motorista atualizarMotorista(String cpf, Motorista motoristaAtualizado);

    // Deletar um motorista
    public void deletarMotorista(String cpf);

}







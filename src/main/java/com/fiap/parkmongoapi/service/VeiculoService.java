package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.veiculo.AtualizaVeiculoDTO;
import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Veiculo;
import org.springframework.stereotype.Service;

@Service
public interface VeiculoService {

    // Cadastrar um veículo
    public Motorista cadastrarVeiculo(String cpf, Veiculo veiculo);

    // Atualizar dados de um veículo
    public Veiculo atualizarVeiculo(String cpf, String placa, AtualizaVeiculoDTO veiculoAtualizado);

    // Deletar um veículo
    public void deletarVeiculo(String cpf, String placa);

    // Consultar um veículo pela placa
    public Veiculo consultarVeiculoPorPlaca(String placa);

    // Consultar um veículo pela placa e CPF do motorista
    public Veiculo consultarVeiculoPorPlacaEMotorista(String cpf,String placa );
}




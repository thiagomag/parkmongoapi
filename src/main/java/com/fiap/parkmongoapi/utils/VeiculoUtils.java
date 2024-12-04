package com.fiap.parkmongoapi.utils;

import com.fiap.parkmongoapi.exception.VagaNotFoundException;
import com.fiap.parkmongoapi.exception.VeiculoNotFoundException;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.repository.VagaRepository;
import com.fiap.parkmongoapi.repository.VeiculoRepository;

import java.util.Optional;

public class VeiculoUtils {


    public static Veiculo buscarVeiculoPorIdOuPlaca(String identificador,String cpfMotorista , VeiculoRepository repository) {
        Optional<Veiculo> veiculoOptional = repository.findById(identificador);

        if (veiculoOptional.isEmpty()) {
            // Se não encontrar pelo ID, tenta buscar pela placa
            veiculoOptional = repository.findByPlacaAndCpfMotorista(identificador,cpfMotorista);
        }

        // Se ainda não encontrou, lança a exceção
        return veiculoOptional.orElseThrow(() -> new VeiculoNotFoundException("Veiculo com identificador "
                + identificador + "e Motorista:" + cpfMotorista +  " não foi encontrado."));
    }

}

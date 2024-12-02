package com.fiap.parkmongoapi.utils;

import com.fiap.parkmongoapi.exception.VagaNotFoundException;
import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class VagaUtils {

    public static String gerarIdCustomizado(Endereco endereco) {
        // Gera um hash baseado no endereço (pode incluir CEP, rua e número)
        String hashEndereco = Integer.toHexString(endereco.hashCode());

        // Gera um UUID curto
        String uuid = UUID.randomUUID().toString().split("-")[0];

        // Combina os dois para formar o ID
        return hashEndereco + "-" + uuid;
    }

    public static Vaga buscarVagaPorIdentificador(String identificador, VagaRepository repository) {
        Optional<Vaga> vagaOptional = repository.findById(identificador);

        if (vagaOptional.isEmpty()) {
            // Se não encontrar pelo ID, tenta buscar pelo locId
            vagaOptional = repository.findByLocId(identificador);
        }

        // Se ainda não encontrou, lança a exceção
        return vagaOptional.orElseThrow(() -> new VagaNotFoundException("Vaga com identificador "
                + identificador + " não encontrada."));
    }






}


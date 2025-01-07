package com.fiap.parkmongoapi.utils;

import com.fiap.parkmongoapi.exception.vaga.VagaNotFoundException;
import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.counter.EnderecoCounter;
import com.fiap.parkmongoapi.repository.VagaRepository;
import com.fiap.parkmongoapi.repository.counterRepository.EnderecoCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VagaUtils {

    private final EnderecoCounterRepository enderecoCounterRepository;

    public String gerarIdCustomizado(Endereco endereco) {
        // Gera o hash do endereço
        String hashEndereco = Integer.toHexString(endereco.hashCode());

        // Obtém o próximo valor do contador ou cria um novo
        EnderecoCounter counter = enderecoCounterRepository.findById(hashEndereco)
                .orElseGet(() -> {
                    EnderecoCounter newCounter = new EnderecoCounter();
                    newCounter.setId(hashEndereco);
                    newCounter.setSequence(0);
                    return newCounter;
                });

        // Incrementa o contador
        int incremento = counter.getSequence() + 1;
        counter.setSequence(incremento);
        enderecoCounterRepository.save(counter);

        // Formata o incremento como 3 dígitos
        String incrementoFormatado = String.format("%03d", incremento);

        // Retorna o ID customizado
        return hashEndereco + "-" + incrementoFormatado;
    }

    public static Vaga buscarVagaPorIdentificador(String identificador, VagaRepository repository) {
        Optional<Vaga> vagaOptional = repository.findById(identificador);

        if (vagaOptional.isEmpty()) {
            // Se não encontrar pelo ID, tenta buscar pelo locId
            vagaOptional = repository.findByLocId(identificador);
        }

        // Se ainda não encontrou, lança a exceção
        return vagaOptional.orElseThrow(() -> new VagaNotFoundException(identificador));
    }






}


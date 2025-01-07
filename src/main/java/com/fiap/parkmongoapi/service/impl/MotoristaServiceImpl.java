package com.fiap.parkmongoapi.service.impl;

import com.fiap.parkmongoapi.exception.motorista.MotoristaAlreadyExistsException;
import com.fiap.parkmongoapi.exception.motorista.MotoristaNotFoundException;
import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.repository.MotoristaRepository;
import com.fiap.parkmongoapi.service.MotoristaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MotoristaServiceImpl implements MotoristaService {

    private final MotoristaRepository motoristaRepository;

    @Override
    public Motorista consultarMotoristaPorCpf(String cpf) {
        return motoristaRepository.findById(cpf)
                .orElseThrow(() -> new MotoristaNotFoundException(cpf));
    }

    @Override
    public Motorista cadastrarMotorista(Motorista motorista) {
        if (motoristaRepository.existsById(motorista.getCpf())) {
            throw new MotoristaAlreadyExistsException(motorista.getCpf());
        }
        return motoristaRepository.save(motorista);
    }

    @Override
    public Motorista atualizarMotorista(String cpf, Motorista motoristaAtualizado) {
        Motorista motoristaExistente = motoristaRepository.findById(cpf)
                .orElseThrow(() -> new MotoristaNotFoundException(cpf));

        // Atualizar campos individualmente, se fornecidos
        if (motoristaAtualizado.getEmail() != null) {
            motoristaExistente.setEmail(motoristaAtualizado.getEmail());
        }
        if (motoristaAtualizado.getNome() != null) {
            motoristaExistente.setNome(motoristaAtualizado.getNome());
        }
        if (motoristaAtualizado.getDataNascimento() != null) {
            motoristaExistente.setDataNascimento(motoristaAtualizado.getDataNascimento());
        }
        if (motoristaAtualizado.getPerfil() != null) {
            motoristaExistente.setPerfil(motoristaAtualizado.getPerfil());
        }

        return motoristaRepository.save(motoristaExistente);
    }

    @Transactional
    @Override
    public void deletarMotorista(String cpf) {
        Motorista motoristaExistente = motoristaRepository.findById(cpf)
                .orElseThrow(() -> new MotoristaNotFoundException(cpf));

        motoristaRepository.delete(motoristaExistente);
    }
}

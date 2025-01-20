package com.fiap.parkmongoapi.service.impl;


import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.veiculo.AtualizaVeiculoDTO;
import com.fiap.parkmongoapi.dto.veiculo.VeiculoResponseDTO;
import com.fiap.parkmongoapi.exception.motorista.MotoristaNotFoundException;
import com.fiap.parkmongoapi.exception.veiculo.NoVehiclesForMotoristException;
import com.fiap.parkmongoapi.exception.veiculo.VeiculoNotFoundException;
import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.repository.MotoristaRepository;
import com.fiap.parkmongoapi.repository.VeiculoRepository;
import com.fiap.parkmongoapi.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final MotoristaRepository motoristaRepository;
    private final VeiculoRepository veiculoRepository;


    @Override
    public Motorista cadastrarVeiculo(String cpfMotorista, Veiculo veiculo) {
        log.info("Cadastrando veículo: " + veiculo);
        // Buscar o motorista pelo CPF
        Motorista motorista = motoristaRepository.findById(cpfMotorista)
                .orElseThrow(() -> {
                    log.error("Motorista não encontrado: " + cpfMotorista);
                    return new MotoristaNotFoundException(cpfMotorista);
                });

        // Salvar o veículo no repositório de veículos
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        // Adicionar o ID do veículo à lista de veículos do motorista
        motorista.getVeiculos().add(veiculoSalvo);

        // Atualizar o motorista com o novo veículo
        motoristaRepository.save(motorista);

        return motorista;
    }

    @Override
    public Veiculo atualizarVeiculo(String cpf, String placa, AtualizaVeiculoDTO veiculoAtualizado) {
        log.info("Atualizando veículo: " + veiculoAtualizado);
        // Buscar o motorista pelo CPF para garantir que ele existe
        Motorista motorista = motoristaRepository.findById(cpf)
                .orElseThrow(() -> {
                    log.error("Motorista não encontrado: " + cpf);
                    return new MotoristaNotFoundException(cpf);
                });

        // Buscar o veículo pela placa diretamente no repositório
        Veiculo veiculoExistente = veiculoRepository.findByPlacaAndCpfMotorista(placa, cpf)
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado: " + placa);
                    return new VeiculoNotFoundException(placa, cpf);
                });

        // Validar que o veículo pertence ao motorista
        if (!motorista.getVeiculos().contains(veiculoExistente)) {
            final var msg = "O veículo não está associado ao motorista com CPF: " + cpf;
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        // Atualizar apenas os campos não nulos do veículo
        if (veiculoAtualizado.placa() != null && !veiculoAtualizado.placa().isBlank()) {
            veiculoExistente.setPlaca(veiculoAtualizado.placa());
        }
        if (veiculoAtualizado.modelo() != null && !veiculoAtualizado.modelo().isBlank()) {
            veiculoExistente.setModelo(veiculoAtualizado.modelo());
        }
        if (veiculoAtualizado.tipoVeiculo() != null) {
            veiculoExistente.setTipoVeiculo(veiculoAtualizado.tipoVeiculo());
        }

        // Salvar o veículo atualizado no repositório
        return veiculoRepository.save(veiculoExistente);
    }


    @Transactional
    @Override
    public void deletarVeiculo(String cpf, String placa) {
        log.info("Deletando veículo com placa: " + placa);
        // Buscar o motorista pelo CPF
        Motorista motorista = motoristaRepository.findById(cpf)
                .orElseThrow(() -> {
                    log.error("Motorista não encontrado: " + cpf);
                    return new MotoristaNotFoundException(cpf);
                });

        // Buscar o veículo pela placa diretamente no repositório
        Veiculo veiculoExistente = veiculoRepository.findByPlacaAndCpfMotorista(placa, cpf)
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado: " + placa);
                    return new VeiculoNotFoundException(placa, cpf);
                });


        // Buscar o veículo pela placa no motorista
        Veiculo veiculo = motorista.getVeiculos().stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst()
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado: " + placa);
                    return new VeiculoNotFoundException(placa);
                });

        // Remover o veículo da lista de veículos do motorista
        motorista.getVeiculos().remove(veiculo);

        // Atualizar o motorista com a lista de veículos atualizada
        motoristaRepository.save(motorista);

        // Deletar o veículo do repositório de veículos
        veiculoRepository.deleteById(veiculo.getId());
    }

    @Transactional
    @Override
    public void deleteByCpfMotorista(String cpf) {
        log.info("Deletando veículos do motorista com CPF: " + cpf);
        List<Veiculo> veiculos = veiculoRepository.findByCpfMotorista(cpf);

        // Verifica se a lista de veículos está vazia
        if (veiculos.isEmpty()) {
            throw new NoVehiclesForMotoristException(cpf);
        }

        veiculoRepository.deleteByCpfMotorista(cpf);

    }


    @Override
    public Veiculo consultarVeiculoPorPlaca(String placa) {
        log.info("Consultando veículo por placa: " + placa);
        // Buscar veículo pela placa
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado: " + placa);
                    return new VeiculoNotFoundException(placa);
                });
    }

    @Override
    public Veiculo consultarVeiculoPorPlacaEMotorista(String cpf, String placa) {
        log.info("Consultando veículo por placa: " + placa);
        // Buscar o veículo na lista de veículos do motorista com a placa informada
        return veiculoRepository.findByPlacaAndCpfMotorista(placa, cpf)
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado: " + placa);
                    return new VeiculoNotFoundException(placa, cpf);
                });
    }

    @Override
    public PageResponseDTO<VeiculoResponseDTO> consultarVeiculosPorMotorista(String cpfMotorista, Pageable pageable) {
        log.info("Consultando veículos do motorista com CPF: " + cpfMotorista);
        // Busca os veículos paginados
        Page<Veiculo> veiculosPage = veiculoRepository.findByCpfMotorista(cpfMotorista, pageable)
                .orElseThrow(() -> {
                    log.info("Veículo não encontrado pelo cpf: " + cpfMotorista);
                    return new VeiculoNotFoundException(cpfMotorista);
                });

        // Converte a página de Veiculo para VeiculoResponseDTO
        Page<VeiculoResponseDTO> veiculoResponseDTOPage = veiculosPage.map(VeiculoResponseDTO::toDTO);


        // Retorna a resposta com os dados da página
        return new PageResponseDTO<>(
                veiculoResponseDTOPage.getContent(),
                veiculoResponseDTOPage.getTotalElements(),
                veiculoResponseDTOPage.getTotalPages(),
                veiculoResponseDTOPage.getNumber(),
                veiculoResponseDTOPage.getSize());
    }
}












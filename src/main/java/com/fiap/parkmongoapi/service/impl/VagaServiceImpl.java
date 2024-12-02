package com.fiap.parkmongoapi.service.impl;

import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.vaga.AtualizaVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.CadastroVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaFiltroDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaResponseDTO;

import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.repository.VagaRepository;
import com.fiap.parkmongoapi.service.VagaService;
import com.fiap.parkmongoapi.utils.VagaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaServiceImpl implements VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VagaUtils vagaUtils;

    @Override
    public Vaga criarVaga(CadastroVagaDTO vaga) {

        Endereco enderecoVaga = vaga.toEndereco();
        String locId = vagaUtils.gerarIdCustomizado(enderecoVaga);

        Vaga vagaCriada = vaga.toEntity(locId);
        vagaCriada.setId(null);

        return vagaRepository.save(vagaCriada);
    }


    @Override
    public Vaga atualizarVaga(String identificador, AtualizaVagaDTO vagaAtualizada) {

        Vaga vagaExistente = VagaUtils.buscarVagaPorIdentificador(identificador, vagaRepository);

        // Atualiza apenas os campos não nulos
        if (vagaAtualizada.perfil() != null) {
            vagaExistente.setPerfil(vagaAtualizada.perfil());
        }
        if (vagaAtualizada.tipoVeiculo() != null) {
            vagaExistente.setTipoVeiculo(vagaAtualizada.tipoVeiculo());
        }
        if (vagaAtualizada.tarifa() != null) {
            vagaExistente.setTarifa(vagaAtualizada.tarifa());
        }

        // Salva e retorna a vaga atualizada
        return vagaRepository.save(vagaExistente);
    }

    @Override
    public void deletarVaga(String identificador) {

        Vaga vaga = VagaUtils.buscarVagaPorIdentificador(identificador, vagaRepository);

        // Remove a vaga
        vagaRepository.delete(vaga);
    }


    @Override
    public PageResponseDTO<VagaResponseDTO> buscarComFiltros(VagaFiltroDTO filtro, Pageable pageable) {
        Query query = new Query();

        // Adiciona filtros condicionais
        if (filtro.cep() != null) { // Filtro para o CEP
            query.addCriteria(Criteria.where("endereco.cep").is(filtro.cep()));
        }

        if (filtro.cidade() != null) {
            query.addCriteria(Criteria.where("endereco.cidade").is(filtro.cidade()));
        }
        if (filtro.uf() != null) {
            query.addCriteria(Criteria.where("endereco.uf").is(filtro.uf()));
        }
        if (filtro.perfil() != null) {
            query.addCriteria(Criteria.where("perfil").is(filtro.perfil()));
        }
        if (filtro.tipoVeiculo() != null) {
            query.addCriteria(Criteria.where("tipoVeiculo").is(filtro.tipoVeiculo()));
        }

        // Paginação
        long totalElements = mongoTemplate.count(query, Vaga.class);
        query.with(pageable);

        List<Vaga> vagas = mongoTemplate.find(query, Vaga.class);

        var vagaResponses =  vagas.stream()
                .map(vaga -> new VagaResponseDTO(
                        vaga.getId(),
                        vaga.getLocId(),
                        vaga.getPerfil(),
                        vaga.getTipoVeiculo(),
                        vaga.getTarifa(),
                        vaga.getEndereco())).toList();


        int totalPages = (int) Math.ceil((double) totalElements / pageable.getPageSize());

        return new PageResponseDTO<>(
                vagaResponses,
                totalElements,
                totalPages,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }


}

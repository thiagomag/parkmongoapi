package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.vaga.AtualizaVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.CadastroVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaFiltroDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaResponseDTO;
import com.fiap.parkmongoapi.model.Endereco;
import com.fiap.parkmongoapi.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VagaService {

    public Vaga criarVaga(CadastroVagaDTO vaga);

    public Vaga atualizarVaga(String id, AtualizaVagaDTO vagaAtualizada);

    // deleta tanto pelo locId quanto pelo id
    void deletarVaga(String id);

    public PageResponseDTO<VagaResponseDTO> buscarComFiltros(VagaFiltroDTO filtro, Pageable pageable);




}

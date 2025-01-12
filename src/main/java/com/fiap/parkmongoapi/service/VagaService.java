package com.fiap.parkmongoapi.service;

import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.vaga.AtualizaVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.CadastroVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaFiltroDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaResponseDTO;
import com.fiap.parkmongoapi.model.Vaga;
import org.springframework.data.domain.Pageable;

public interface VagaService {

    Vaga criarVaga(CadastroVagaDTO vaga);

    Vaga atualizarVaga(String id, AtualizaVagaDTO vagaAtualizada);

    // deleta tanto pelo locId quanto pelo id
    void deletarVaga(String id);

    PageResponseDTO<VagaResponseDTO> buscarComFiltros(VagaFiltroDTO filtro, Pageable pageable);
}

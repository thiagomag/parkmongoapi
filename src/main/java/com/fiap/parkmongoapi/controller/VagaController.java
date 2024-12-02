package com.fiap.parkmongoapi.controller;

import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.vaga.AtualizaVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.CadastroVagaDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaFiltroDTO;
import com.fiap.parkmongoapi.dto.vaga.VagaResponseDTO;
import com.fiap.parkmongoapi.model.Vaga;
import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import com.fiap.parkmongoapi.model.enums.EnumTipoVeiculo;
import com.fiap.parkmongoapi.service.VagaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vaga")
@Tag(name = "Vaga", description = "API para gerenciar vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    public ResponseEntity<Vaga> criarVaga(@Valid @RequestBody CadastroVagaDTO vaga) {

        Vaga vagaCriada = vagaService.criarVaga(vaga);
        return ResponseEntity.ok(vagaCriada);
    }


    @PutMapping("/{identificador}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable String identificador,
                                              @Valid @RequestBody AtualizaVagaDTO vagaDTO) {

        Vaga vagaAtualizada = vagaService.atualizarVaga(identificador, vagaDTO);

        return ResponseEntity.ok(vagaAtualizada);
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<Void> deletarVaga(@PathVariable String identificador) {
        vagaService.deletarVaga(identificador);
        return ResponseEntity.noContent().build();
    }

    // Get para os parametros cep, cidade, uf, perfil e tipo veiculo
    @GetMapping
    public ResponseEntity<PageResponseDTO<VagaResponseDTO>> buscarComFiltros(
            @RequestParam(required = false) String cep,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) EnumPerfil perfil,
            @RequestParam(required = false) EnumTipoVeiculo tipoVeiculo,
            @RequestParam(defaultValue = "0") int page, // valor padrão 0
            @RequestParam(defaultValue = "5") int size)
            {

        Pageable pageable = PageRequest.of(page, size);

        VagaFiltroDTO filtro = new VagaFiltroDTO(cep, cidade, uf, perfil, tipoVeiculo);

        PageResponseDTO<VagaResponseDTO> vagas = vagaService.buscarComFiltros(filtro, pageable);

        return ResponseEntity.ok(vagas);
    }













}

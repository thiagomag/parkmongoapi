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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "Criar uma nova vaga",
            description = "Cria uma nova vaga com as informações fornecidas no corpo da requisição.")
    public ResponseEntity<VagaResponseDTO> criarVaga(@Valid @RequestBody CadastroVagaDTO vaga) {

        Vaga vagaCriada = vagaService.criarVaga(vaga);

        var dto = VagaResponseDTO.toDto(vagaCriada);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{identificador}")
    @Operation(summary = "Atualizar uma vaga existente",
            description = "Atualiza os dados de uma vaga existente com base no identificador fornecido.")
    public ResponseEntity<VagaResponseDTO> atualizarVaga(@PathVariable String identificador,
                                              @Valid @RequestBody AtualizaVagaDTO vagaDTO) {

        Vaga vagaAtualizada = vagaService.atualizarVaga(identificador, vagaDTO);

        var dto = VagaResponseDTO.toDto(vagaAtualizada);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{identificador}")
    @Operation(summary = "Deletar uma vaga",
            description = "Deleta uma vaga com base no identificador fornecido.")
    public ResponseEntity<Void> deletarVaga(@PathVariable String identificador) {
        vagaService.deletarVaga(identificador);
        return ResponseEntity.noContent().build();
    }

    // Get para os parametros cep, cidade, uf, perfil e tipo veiculo
    @GetMapping
    @Operation(summary = "Buscar vagas com filtros",
            description = "Busca vagas com base nos filtros fornecidos como parâmetros de consulta.")
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

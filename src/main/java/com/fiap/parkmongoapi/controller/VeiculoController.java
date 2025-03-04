package com.fiap.parkmongoapi.controller;

import com.fiap.parkmongoapi.dto.PageResponseDTO;
import com.fiap.parkmongoapi.dto.motorista.MotoristaResponseDTO;
import com.fiap.parkmongoapi.dto.veiculo.AtualizaVeiculoDTO;
import com.fiap.parkmongoapi.dto.veiculo.CadastroVeiculoDTO;
import com.fiap.parkmongoapi.dto.veiculo.VeiculoResponseDTO;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.service.VeiculoService;
import com.fiap.parkmongoapi.validations.ValidCpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/veiculo")
@RequiredArgsConstructor
@Tag(name = "Veiculo", description = "API para gerenciar veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping("/{cpfMotorista}")
    @Operation(summary = "Cadastrar Veiculos de Motorista", description = "Cadastra um novo veiculo vinculado a um cpf.")
    public ResponseEntity<MotoristaResponseDTO> cadastrarVeiculos(
            @PathVariable @Valid @ValidCpf String cpfMotorista,
            @Valid @RequestBody CadastroVeiculoDTO veiculoDTO){

        var veiculo = new Veiculo();

        veiculo.setPlaca(veiculoDTO.placa());
        veiculo.setModelo(veiculoDTO.modelo());
        veiculo.setTipoVeiculo(veiculoDTO.tipoVeiculo());
        veiculo.setCpfMotorista(cpfMotorista);

        var motorista = veiculoService.cadastrarVeiculo(cpfMotorista,veiculo);
        var dto = MotoristaResponseDTO.toDTO(motorista);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpfMotorista}/{placa}")
                .buildAndExpand(cpfMotorista, veiculo.getPlaca())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Atualizar um o Veiculo de um Motorista por placa",
            description = "Retorna o Veiculo atualizado")
    public ResponseEntity<VeiculoResponseDTO> atualizarVeiculo(
            @PathVariable @Valid @ValidCpf String cpfMotorista,
            @PathVariable String placa,
            @RequestBody AtualizaVeiculoDTO veiculoAtualizado) {

        Veiculo veiculoAtualizadoResponse = veiculoService.atualizarVeiculo(cpfMotorista, placa, veiculoAtualizado);
        var dto = VeiculoResponseDTO.toDTO(veiculoAtualizadoResponse);

        return ResponseEntity.ok(dto);
    }



    @DeleteMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Deleta um o Veiculo de um Motorista por placa",
            description = "Deleta um Veiculo de um Motorista por placa")
    public ResponseEntity<Void> deletarVeiculo (
            @PathVariable @Valid @ValidCpf String cpfMotorista,
            @PathVariable String placa){

        this.veiculoService.deletarVeiculo(cpfMotorista, placa);

        return ResponseEntity.noContent().build();}



    @DeleteMapping("/motorista/{cpfMotorista}")
    @Operation(summary = "Excluir todos os veículos de um motorista",
            description = "Exclui todos os veículos associados a um CPF de motorista.")
    public ResponseEntity<Void> deleteByCpfMotorista(@PathVariable @Valid @ValidCpf String cpfMotorista) {
        veiculoService.deleteByCpfMotorista(cpfMotorista);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{placa}")
    @Operation(summary = "Consultar Veiculo por placa",
            description = "Retorna um veiculo com base na placa fornecido.")
    public ResponseEntity<VeiculoResponseDTO> consultarVeiculoPorPlaca(
            @PathVariable String placa){

        var veiculo = this.veiculoService.consultarVeiculoPorPlaca(placa);
        var dto = VeiculoResponseDTO.toDTO(veiculo);

        return ResponseEntity.ok(dto);
    }


    @GetMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Consultar Veiculo de um motorista por placa",
            description = "Retorna um veiculo  de um motorista com base na placa fornecido.")
    public ResponseEntity<VeiculoResponseDTO> consultarVeiculoPorPlacaEMotorista(
            @PathVariable @Valid @ValidCpf String cpfMotorista,
            @PathVariable String placa){

        var veiculo = this.veiculoService.consultarVeiculoPorPlacaEMotorista(cpfMotorista,placa);
        var dto = VeiculoResponseDTO.toDTO(veiculo);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/motorista/{cpfMotorista}")
    @Operation(summary = "Consultar Todos Veiculos de um motorista",
            description = "Retorna todos os veiculos associados a um cpf de um motorista.")
    public ResponseEntity<PageResponseDTO<VeiculoResponseDTO>>  consultarVeiculosPorMotorista(
            @PathVariable @Valid @ValidCpf String cpfMotorista,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        var veiculoPaginacao = this.veiculoService.consultarVeiculosPorMotorista(cpfMotorista,pageable);


        return ResponseEntity.ok(veiculoPaginacao);
    }










}


package com.fiap.parkmongoapi.controller;

import com.fiap.parkmongoapi.dto.veiculo.AtualizaVeiculoDTO;
import com.fiap.parkmongoapi.dto.veiculo.CadastroVeiculoDTO;
import com.fiap.parkmongoapi.model.Motorista;
import com.fiap.parkmongoapi.model.Veiculo;
import com.fiap.parkmongoapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/veiculo")
@Tag(name = "Veiculo", description = "API para gerenciar veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/{cpfMotorista}")
    @Operation(summary = "Cadastrar Veiculos de Motorista", description = "Cadastra um novo veiculo vinculado a um cpf.")
    public ResponseEntity<Motorista> cadastrarVeiculos(
            @PathVariable
            @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
            String cpfMotorista,
            @Valid @RequestBody CadastroVeiculoDTO veiculoDTO){

        var veiculo = new Veiculo();

        veiculo.setPlaca(veiculoDTO.placa());
        veiculo.setModelo(veiculoDTO.modelo());
        veiculo.setTipoVeiculo(veiculoDTO.tipoVeiculo());
        veiculo.setCpfMotorista(cpfMotorista);

        var motorista = veiculoService.cadastrarVeiculo(cpfMotorista,veiculo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpfMotorista}/{placa}")
                .buildAndExpand(cpfMotorista, veiculo.getPlaca())
                .toUri();

        return ResponseEntity.created(location).body(motorista);
    }

    @PutMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Atualizar um o Veiculo de um Motorista por placa",
            description = "Retorna o Veiculo atualizado")
    public ResponseEntity<Veiculo> atualizarVeiculo(
            @PathVariable String cpfMotorista,
            @PathVariable String placa,
            @RequestBody AtualizaVeiculoDTO veiculoAtualizado) {

        Veiculo veiculoAtualizadoResponse = veiculoService.atualizarVeiculo(cpfMotorista, placa, veiculoAtualizado);
        return ResponseEntity.ok(veiculoAtualizadoResponse);
    }



    @DeleteMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Deleta um o Veiculo de um Motorista por placa",
            description = "Deleta um Veiculo de um Motorista por placa")
    public ResponseEntity<String> deletarVeiculo (
            @PathVariable String cpfMotorista,
            @PathVariable String placa){

        this.veiculoService.deletarVeiculo(cpfMotorista, placa);

        return ResponseEntity.noContent().build();}

    @GetMapping("/{placa}")
    @Operation(summary = "Consultar Veiculo por placa",
            description = "Retorna um veiculo com base na placa fornecido.")
    public ResponseEntity<Veiculo> consultarVeiculoPorPlaca(
            @PathVariable String placa){

        var veiculo = this.veiculoService.consultarVeiculoPorPlaca(placa);

        return ResponseEntity.ok(veiculo);
    }


    @GetMapping("/{cpfMotorista}/{placa}")
    @Operation(summary = "Consultar Veiculo de um motorista por placa",
            description = "Retorna um veiculo  de um motorista com base na placa fornecido.")
    public ResponseEntity<Veiculo> consultarVeiculoPorPlacaEMotorista(
            @PathVariable String cpfMotorista,
            @PathVariable String placa){

        var veiculo = this.veiculoService.consultarVeiculoPorPlacaEMotorista(cpfMotorista,placa);

        return ResponseEntity.ok(veiculo);
    }









}


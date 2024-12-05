package com.fiap.parkmongoapi.exception.veiculo;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class VeiculoNotFoundException extends EntityNotFoundException {

    public VeiculoNotFoundException(String identificador) {
        super("Veiculo não encontrado com id ou placa: " + identificador);
    }




    public VeiculoNotFoundException(String identificador, String cpfMotorista) {
        super("Nenhum veiculo encontrado com id ou placa: " + identificador +
                "para o motorista de cpf: " + cpfMotorista );
    }



}

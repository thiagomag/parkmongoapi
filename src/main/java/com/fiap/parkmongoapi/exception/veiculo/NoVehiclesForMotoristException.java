package com.fiap.parkmongoapi.exception.veiculo;

import com.fiap.parkmongoapi.exception.EntityNotFoundException;

public class NoVehiclesForMotoristException extends EntityNotFoundException {
    public NoVehiclesForMotoristException(String cpf) {
        super("Nenhum veículo encontrado para o motorista com o CPF: " + cpf);
    }
}

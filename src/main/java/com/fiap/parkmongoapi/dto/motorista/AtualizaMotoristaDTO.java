package com.fiap.parkmongoapi.dto.motorista;

import com.fiap.parkmongoapi.model.enums.EnumPerfil;

import java.time.LocalDate;

public record AtualizaMotoristaDTO(
        String nome,
        LocalDate dataNascimento,
        String email,
        EnumPerfil perfil

) { }


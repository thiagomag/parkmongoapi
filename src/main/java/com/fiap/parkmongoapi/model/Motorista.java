package com.fiap.parkmongoapi.model;

import com.fiap.parkmongoapi.model.enums.EnumPerfil;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorista {

    @Id
    @NotBlank // Não pode ser nulo nem vazio
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    private String cpf;

    @NotBlank
    private String nome;

    @Past(message = "A data de nascimento deve ser anterior à data atual.")
    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private EnumPerfil perfil;

    @DBRef
    private List<Veiculo> veiculos;

}

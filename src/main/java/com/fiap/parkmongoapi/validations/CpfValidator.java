package com.fiap.parkmongoapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public void initialize(ValidCpf constraintAnnotation) {
        // Não é necessário fazer nada aqui
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }

        // Remove qualquer caractere não numérico
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem exatamente 11 dígitos
        return cpf.matches("\\d{11}");
    }
}

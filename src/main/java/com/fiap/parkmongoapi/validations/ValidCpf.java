package com.fiap.parkmongoapi.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER }) // Pode ser usada em campos e parâmetros
@Retention(RetentionPolicy.RUNTIME) // A anotação estará disponível em tempo de execução
public @interface ValidCpf {
    String message() default "CPF inválido"; // Mensagem padrão de erro
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

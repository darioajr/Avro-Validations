package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.generated.avro.Exemplo;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

@Service
public class ValidadorService {

    @Autowired
    private Validator validator;

    public void validarDados(Exemplo dados) {
        Set<ConstraintViolation<Exemplo>> violations = validator.validate(dados);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> System.out.println(violation.getMessage()));
            // Tratamento de erro ou lançamento de exceção conforme necessário
            throw new RuntimeException("Validação falhou para os dados: " + violations);
        }
    }
}

package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.generated.avro.Exemplo;

import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
class DemoApplicationTests {

	private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	void quandoAsInformacoesEstaoCorretas_EntaoNaoHaViolacoes() {
		Exemplo dados = new Exemplo();
        dados.setNumero(35);
        dados.setIndicador(40);
        dados.setTexto("Um texto válido");
        
        Set<ConstraintViolation<Exemplo>> violations = validator.validate(dados);
		assertTrue(violations.isEmpty());
	}

	@Test
	void quandoONumeroDoRangeUmForIvalido_EntaoHaViolacoes() {
		Exemplo dados = new Exemplo();
        dados.setNumero(51);
        
        Set<ConstraintViolation<Exemplo>> violations = validator.validate(dados);
		assertEquals(1, violations.size());
        assertEquals("O valor deve ser no mínimo 100 se for maior que 50", violations.iterator().next().getMessage());
	}

	@Test
	void quandoONumeroDoRangeUmForValido_EntaoNaoHaViolacoes() {
		Exemplo dados = new Exemplo();
        dados.setNumero(50);
        
        Set<ConstraintViolation<Exemplo>> violations = validator.validate(dados);
		assertTrue(violations.isEmpty());
	}

}

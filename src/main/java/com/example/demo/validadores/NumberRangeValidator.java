package com.example.demo.validadores;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberRangeValidator implements ConstraintValidator<NumberRange, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;  // Consider null as valid or adjust based on your requirements
        return (value >= 0 && value <= 50) || (value >= 100 && value <= 150);
    }
}

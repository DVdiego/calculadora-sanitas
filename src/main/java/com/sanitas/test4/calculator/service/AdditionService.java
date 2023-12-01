package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class AdditionService implements OperationService {
    @Override
    public BigDecimal performOperation(List<BigDecimal> numbers) {
        if (numbers.isEmpty()) {
            throw new InvalidOperationException("The list of numbers cannot be empty for addition.");
        }
        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

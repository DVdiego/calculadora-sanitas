package com.sanitas.test4.calculator.service;

import java.math.BigDecimal;
import java.util.List;

public class AdditionService implements OperationService {
    @Override
    public BigDecimal performOperation(List<BigDecimal> numbers) {
        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

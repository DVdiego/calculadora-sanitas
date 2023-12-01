package com.sanitas.test4.calculator.service;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {

    BigDecimal performOperation(List<BigDecimal> numbers);
}

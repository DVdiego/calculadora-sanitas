package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubtractionService implements OperationService {

    private final ExceptionMessages exceptionMessages;

    public SubtractionService(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    @Override
    public BigDecimal performOperation(List<BigDecimal> numbers) {
        if (numbers.size() < 2) {
            throw new InvalidOperationException(this.exceptionMessages.getMessages().get("list-numbers-wrong"));
        }

        BigDecimal result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = result.subtract(numbers.get(i));
        }

        return result;
    }
}

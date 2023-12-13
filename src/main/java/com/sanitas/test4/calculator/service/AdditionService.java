package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class AdditionService implements OperationService {

    private final ExceptionMessages exceptionMessages;

    public AdditionService(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    @Override
    public BigDecimal performOperation(List<BigDecimal> numbers) {
        if (numbers.size() < 2) {
            throw new InvalidOperationException(this.exceptionMessages.getMessages().get("list-numbers-wrong"));
        }
        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

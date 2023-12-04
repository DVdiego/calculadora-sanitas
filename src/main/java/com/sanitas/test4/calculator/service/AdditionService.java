package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class AdditionService implements OperationService {

    @Autowired
    private ExceptionMessages exceptionMessages;
    @Override
    public BigDecimal performOperation(List<BigDecimal> numbers) {
        if (numbers.size() < 2) {
            throw new InvalidOperationException(this.exceptionMessages.getMessages().get("list-numbers-wrong"));
        }
        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

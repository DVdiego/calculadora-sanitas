package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.model.OperationProvider;
import com.sanitas.test4.calculator.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperationFactory {

    private final Map<String, OperationService> operations;

    @Autowired
    public OperationFactory(OperationProvider operationProvider) {
        this.operations = operationProvider.getOperations();
    }

    public OperationService getOperation(String operationType) {
        return operations.computeIfAbsent(operationType.toLowerCase(), key -> {
            throw new InvalidOperationException("Operation not supported");
        });
    }

}

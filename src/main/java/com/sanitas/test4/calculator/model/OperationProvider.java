package com.sanitas.test4.calculator.model;

import com.sanitas.test4.calculator.service.OperationService;

import java.util.Map;

public interface OperationProvider {
    Map<String, OperationService> getOperations();
}

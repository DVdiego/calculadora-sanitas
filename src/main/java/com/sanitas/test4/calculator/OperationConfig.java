package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.model.OperationProvider;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.OperationService;
import com.sanitas.test4.calculator.service.SubtractionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OperationConfig implements OperationProvider {
    @Bean
    public Map<String, OperationService> getOperations() {
        Map<String, OperationService> operations = new HashMap<>();
        operations.put("addition", new AdditionService());
        operations.put("subtraction", new SubtractionService());
        return operations;
    }
}

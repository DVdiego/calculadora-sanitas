package com.sanitas.test4.calculator.configuration;

import com.sanitas.test4.calculator.model.OperationProvider;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.OperationService;
import com.sanitas.test4.calculator.service.SubtractionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OperationConfig implements OperationProvider {

    @Value("${operation.supported.addition}")
    private String addition;

    @Value("${operation.supported.subtraction}")
    private String subtraction;

    @Bean
    public Map<String, OperationService> getOperations() {
        Map<String, OperationService> operations = new HashMap<>();
        operations.put(this.addition, new AdditionService());
        operations.put(this.subtraction, new SubtractionService());
        return operations;
    }
}

package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.OperationService;
import com.sanitas.test4.calculator.service.SubtractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class OperationFactoryTest {

    @Value("${operation.supported.addition}")
    private String addition;

    @Value("${operation.supported.subtraction}")
    private String subtraction;

    @Autowired
    private OperationFactory operationFactory;

    @Test
    void shouldReturnAdditionServiceForAdditionOperation() {
        OperationService operationService = this.operationFactory.getOperation(this.addition);
        assertSame(AdditionService.class, operationService.getClass());
    }

    @Test
    void shouldReturnSubtractionServiceForSubtractionOperation() {
        OperationService operationService = this.operationFactory.getOperation(this.subtraction);
        assertSame(SubtractionService.class, operationService.getClass());
    }

    @Test
    void shouldThrowExceptionForInvalidOperation() {
        assertThrows(InvalidOperationException.class, () -> this.operationFactory.getOperation("otherOperation"));
    }

}

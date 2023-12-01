package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.OperationService;
import com.sanitas.test4.calculator.service.SubtractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class OperationFactoryTest {

    @Autowired
    private OperationFactory operationFactory;

    @Test
    void shouldReturnAdditionServiceForAdditionOperation() {
        OperationService operationService = this.operationFactory.getOperation("addition");
        assertSame(AdditionService.class, operationService.getClass());
    }

    @Test
    void shouldReturnSubtractionServiceForSubtractionOperation() {
        OperationService operationService = this.operationFactory.getOperation("subtraction");
        assertSame(SubtractionService.class, operationService.getClass());
    }

    @Test
    void shouldThrowExceptionForInvalidOperation() {
        assertThrows(InvalidOperationException.class, () -> operationFactory.getOperation("otherOperation"));
    }

}

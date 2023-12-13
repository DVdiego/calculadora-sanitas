package com.sanitas.test4.calculator.factory;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.model.OperationProvider;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.OperationService;
import com.sanitas.test4.calculator.service.SubtractionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OperationFactoryTest {

    @Mock
    private ExceptionMessages exceptionMessages;

    @Mock
    private OperationProvider operationProvider;

    @InjectMocks
    private OperationFactory operationFactory;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        when(this.exceptionMessages.getMessages()).thenReturn(new HashMap<>());
        when(this.operationProvider.getOperations()).thenReturn(createMockOperations());
        this.operationFactory = new OperationFactory(this.operationProvider, this.exceptionMessages);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        this.closeable.close();
    }

    private Map<String, OperationService> createMockOperations() {
        Map<String, OperationService> mockOperations = new HashMap<>();
        mockOperations.put("+", mock(AdditionService.class));
        mockOperations.put("-", mock(SubtractionService.class));
        return mockOperations;
    }

    @Test
    void testGetOperation_Addition() {
        OperationService operationService = this.operationFactory.getOperation("+");
        assertNotNull(operationService);
        assertTrue(operationService instanceof AdditionService);
        verifyNoInteractions(this.exceptionMessages);
    }

    @Test
    void testGetOperation_Subtraction() {
        OperationService operationService = this.operationFactory.getOperation("-");
        assertNotNull(operationService);
        assertTrue(operationService instanceof SubtractionService);
        verifyNoInteractions(this.exceptionMessages);
    }

    @Test
    void testGetOperation_InvalidOperation() {
        assertThrows(InvalidOperationException.class, () -> this.operationFactory.getOperation("invalidOperation"));
        verify(this.exceptionMessages).getMessages();
    }

}

package com.sanitas.test4.calculator.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidOperationExceptionTest {

    @Test
    void constructorShouldSetMessage() {
        String message = "Test message";
        InvalidOperationException exception = new InvalidOperationException(message);

        assertEquals(message, exception.getMessage());
    }

}
package com.sanitas.test4.calculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OperationResponseTest {

    private OperationResponse response1;
    private OperationResponse response2;
    private OperationResponse response3;
    @BeforeEach
    void setUp() {
        response1 = new OperationResponse("2+3", BigDecimal.valueOf(5));
        response2 = new OperationResponse("2+3", BigDecimal.valueOf(5));
        response3 = new OperationResponse("1+1", BigDecimal.valueOf(2));
    }

    @Test
    void testEquals() {
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }

    @Test
    void canEqual() {
        assertTrue(response1.canEqual(response2));
        assertTrue(response1.canEqual(response3));
    }

    @Test
    void testHashCode() {
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        OperationResponse response = new OperationResponse("2+3", BigDecimal.valueOf(5));
        String expected = "OperationResponse(expression=2+3, result=5)";
        assertEquals(expected, response.toString());
    }
}
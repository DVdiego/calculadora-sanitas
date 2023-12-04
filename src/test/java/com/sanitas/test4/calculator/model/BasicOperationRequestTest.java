package com.sanitas.test4.calculator.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasicOperationRequestTest {

    @Autowired
    private Validator validator;
    private BasicOperationRequest operation1;
    private BasicOperationRequest operation2;
    private BasicOperationRequest operation3;

    @BeforeEach
    void setUp() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        this.operation1 = new BasicOperationRequest();
        this.operation1.setOperation("+");
        this.operation1.setNumbers(numbers);

        this.operation2 = new BasicOperationRequest();
        this.operation2.setOperation("+");
        this.operation2.setNumbers(numbers);

        this.operation3 = new BasicOperationRequest();
        this.operation3.setOperation("-");
        this.operation3.setNumbers(Arrays.asList(BigDecimal.valueOf(4), BigDecimal.valueOf(5), BigDecimal.valueOf(6)));
    }

    @Test
    void gettersAndSettersShouldWorkCorrectly() {
        BasicOperationRequest request = new BasicOperationRequest();
        request.setOperation("+");
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        request.setNumbers(numbers);

        assertEquals("+", request.getOperation());
        assertEquals(numbers, request.getNumbers());
    }

    @Test
    void testValidBasicOperationRequest() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.ONE, BigDecimal.TEN);
        BasicOperationRequest request = new BasicOperationRequest();
        request.setOperation("+");
        request.setNumbers(numbers);
        BindingResult errors = new BeanPropertyBindingResult(request, "request");
        this.validator.validate(request, errors);

        assertTrue(errors.getAllErrors().isEmpty());
    }

    @Test
    void testEquals() {
        assertEquals(this.operation1, this.operation2);
        assertNotEquals(this.operation1, this.operation3);
    }

    @Test
    void canEqual() {
        assertTrue(this.operation1.canEqual(this.operation2));
    }

    @Test
    void testHashCode() {
        assertEquals(this.operation1.hashCode(), this.operation2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("BasicOperationRequest(operation=+, numbers=[1, 2, 3])", this.operation1.toString());
    }
}
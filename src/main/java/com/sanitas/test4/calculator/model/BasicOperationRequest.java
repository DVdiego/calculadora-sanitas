package com.sanitas.test4.calculator.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BasicOperationRequest {

    private String operation;
    private List<BigDecimal> numbers;

}

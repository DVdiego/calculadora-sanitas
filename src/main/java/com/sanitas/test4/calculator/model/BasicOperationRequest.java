package com.sanitas.test4.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicOperationRequest {

    @Schema(
            description = "Type of operation to perform. Supported values: '+', '-'",
            example = "+"
    )
    private String operation;
    @Schema(
            description = "List of numbers to operate on.",
            example = "[2, 3, 5]"
    )
    private List<BigDecimal> numbers;

}

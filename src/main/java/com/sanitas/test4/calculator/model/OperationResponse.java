package com.sanitas.test4.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {

    @Schema( description="A combination of numbers, variables, and operators (such as +, -, *, /) that represents a arithmetical operation" )
    String expression;

    @Schema( description="The value obtained after performing a arithmetical Operation" )
    BigDecimal result;
}

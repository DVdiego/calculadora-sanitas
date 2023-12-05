package com.sanitas.test4.calculator.controller;

import com.sanitas.test4.calculator.exception.CalculatorException;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.model.ApiErrorResponse;
import com.sanitas.test4.calculator.factory.OperationFactory;
import com.sanitas.test4.calculator.model.BasicOperationRequest;
import com.sanitas.test4.calculator.service.OperationService;
import io.corp.calculator.TracerAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private OperationFactory operationFactory;
    @Autowired
    private TracerAPI tracerAPI;

    @PostMapping("/v1/perform")
    @Operation(summary = "Perform basic arithmetic operation.",
            description = "Performs addition or subtraction operations on the numbers provided as parameters",
            responses = {
            @ApiResponse(responseCode = "200", description = "Result of the operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "400", description = "Operation not supported",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })    public ResponseEntity<BigDecimal> perform(@RequestBody BasicOperationRequest request) throws CalculatorException {
        try {
            OperationService operationService = this.operationFactory.getOperation(request.getOperation());
            BigDecimal result = operationService.performOperation(request.getNumbers());
            String arithmeticOperation = String.join(" " + request.getOperation() + " ", request.getNumbers().stream().map(BigDecimal::toString).toArray(String[]::new));
            this.tracerAPI.trace("Operation result Successfully: " + arithmeticOperation + " = " + result);
            return ResponseEntity.ok(result);
        } catch (InvalidOperationException invalidOperationException) {
            throw invalidOperationException;
        } catch (Exception ex) {
            throw new CalculatorException(ex.getMessage(), ex);
        }
    }


}

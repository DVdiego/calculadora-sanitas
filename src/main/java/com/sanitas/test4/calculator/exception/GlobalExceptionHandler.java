package com.sanitas.test4.calculator.exception;


import com.sanitas.test4.calculator.model.ApiErrorResponse;
import io.corp.calculator.TracerAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private TracerAPI tracerAPI;

    @Autowired
    private ExceptionMessages exceptionMessages;

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleInvalidOperation(InvalidOperationException ex) {
        String message = this.exceptionMessages.getMessages().get("invalid-operation");
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setDetails(ex.getMessage());

        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        this.tracerAPI.trace(response);
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = this.exceptionMessages.getMessages().get("incorrect-data-format");
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setDetails(ex.getMessage());

        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        this.tracerAPI.trace(response);
        return response;
    }

    @ExceptionHandler(CalculatorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleException(CalculatorException ex) {
        String message = this.exceptionMessages.getMessages().get("generic-exception");
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setDetails(ex.getMessage());

        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        this.tracerAPI.trace(response);
        return response;
    }

}

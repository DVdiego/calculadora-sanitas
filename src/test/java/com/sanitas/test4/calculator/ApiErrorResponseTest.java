package com.sanitas.test4.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    @Test
    void createApiErrorResponse() {

        String message = "Test Message";
        String details = "Test Details";

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setDetails(details);

        assertEquals(message, apiErrorResponse.getMessage());
        assertEquals(details, apiErrorResponse.getDetails());
    }

}
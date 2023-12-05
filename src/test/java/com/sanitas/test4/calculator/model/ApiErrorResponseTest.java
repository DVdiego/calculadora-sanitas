package com.sanitas.test4.calculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    private ApiErrorResponse apiErrorResponse1;
    private ApiErrorResponse apiErrorResponse2;

    private ApiErrorResponse apiErrorResponse3;
    @BeforeEach
    void setUp() {
        String message = "Test Message";
        String details = "Test Details";

        apiErrorResponse1 = new ApiErrorResponse();
        apiErrorResponse1.setMessage(message);
        apiErrorResponse1.setDetails(details);


        apiErrorResponse2 = new ApiErrorResponse();
        apiErrorResponse2.setMessage(message);
        apiErrorResponse2.setDetails(details);

        apiErrorResponse3 = new ApiErrorResponse();
        apiErrorResponse3.setMessage("Test Message3");
        apiErrorResponse3.setDetails("Test Details3");
    }
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

    @Test
    void testEquals() {
        assertEquals(this.apiErrorResponse1, this.apiErrorResponse2);
        assertNotEquals(this.apiErrorResponse1, this.apiErrorResponse3);
    }

    @Test
    void canEqual() {
        assertTrue(this.apiErrorResponse1.canEqual(this.apiErrorResponse2));
    }

    @Test
    void testHashCode() {
        assertEquals(this.apiErrorResponse1.hashCode(), this.apiErrorResponse2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("ApiErrorResponse(message=Test Message, details=Test Details)", this.apiErrorResponse1.toString());
    }
}
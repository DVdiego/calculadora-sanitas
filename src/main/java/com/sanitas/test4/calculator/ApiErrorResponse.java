package com.sanitas.test4.calculator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Object representing the standard error for the API.")
public class ApiErrorResponse {
    private String message;
    private String details;
}

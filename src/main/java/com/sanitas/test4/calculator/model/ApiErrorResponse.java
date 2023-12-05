package com.sanitas.test4.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Object representing the standard error for the API.")
public class ApiErrorResponse {
    @Schema(description = "Error Message")
    private String message;
    @Schema(description = "Error description")
    private String details;
}

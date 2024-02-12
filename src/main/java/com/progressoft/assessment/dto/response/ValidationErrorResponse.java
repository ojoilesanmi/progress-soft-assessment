package com.progressoft.assessment.dto.response;

import com.progressoft.assessment.data.enums.ErrorCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ValidationErrorResponse {
    private String message;
    private ErrorCode error;
    private Map<String, String> validationErrors = new HashMap<>();

    public void addValidationError(String field, String message) {
        validationErrors.put(field, message);
    }
}

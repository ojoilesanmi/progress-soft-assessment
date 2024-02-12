package com.progressoft.assessment.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assessment.data.enums.ErrorCode;
import com.progressoft.assessment.dto.response.ErrorResponseHandler;
import com.progressoft.assessment.dto.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) throws JsonProcessingException {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.setMessage("Validation failed");
        validationErrorResponse.setError(ErrorCode.VALIDATION_ERROR);

        for (FieldError fieldError : fieldErrors) {
            validationErrorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("Validation failed === > {}", new ObjectMapper().writeValueAsString(validationErrorResponse));
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(DuplicateException ex) throws JsonProcessingException {
        ErrorResponseHandler errorResponse = new ErrorResponseHandler();
        errorResponse.setMessage("Duplicate entry");
        errorResponse.setError(ErrorCode.DUPLICATE_ERROR);

        log.info("Duplicate entry === > {}", new ObjectMapper().writeValueAsString(errorResponse));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}


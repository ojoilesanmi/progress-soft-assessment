package com.progressoft.assessment.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assessment.data.enums.ErrorCode;
import com.progressoft.assessment.dto.response.ErrorResponseHandler;
import com.progressoft.assessment.dto.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) throws JsonProcessingException {
        if (Objects.requireNonNull(ex.getRootCause()).getMessage().contains("VALUE_NUMBER_INT is not a `String` value!")) {

            ErrorResponseHandler errorResponse = new ErrorResponseHandler();
            errorResponse.setMessage("Invalid data type");
            errorResponse.setError(ErrorCode.INVALID_DATA_TYPE);

            log.info("Invalid data type === > {}", new ObjectMapper().writeValueAsString(errorResponse));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else {

            log.error("Error occurred during JSON parsing", ex);
            ErrorResponseHandler errorResponse = new ErrorResponseHandler();
            errorResponse.setMessage("Failed to parse JSON request");
            errorResponse.setError(ErrorCode.JSON_PARSING_ERROR);

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}


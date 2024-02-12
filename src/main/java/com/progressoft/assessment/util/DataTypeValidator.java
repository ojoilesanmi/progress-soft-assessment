package com.progressoft.assessment.util;

import com.progressoft.assessment.annotations.ValidateDataType;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataTypeValidator implements ConstraintValidator<ValidateDataType, Object> {

    private ValidateDataType.DataType dataType;

    @Override
    public void initialize(ValidateDataType constraintAnnotation) {
        this.dataType = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return switch (dataType) {
            case STRING -> !(value instanceof String);
            case INTEGER -> !(value instanceof Integer);
            case DOUBLE -> !(value instanceof Double);
            case BOOLEAN -> !(value instanceof Boolean);
            case BIG_DECIMAL -> !(value instanceof BigDecimal);

        };
    }

}

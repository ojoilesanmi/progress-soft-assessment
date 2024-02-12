package com.progressoft.assessment.annotations;

import com.progressoft.assessment.util.DataTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DataTypeValidator.class)
public @interface ValidateDataType {
    DataType value();

    String message() default "Invalid data type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    enum DataType {
        STRING,
        INTEGER,
        DOUBLE,
        BOOLEAN,
        BIG_DECIMAL
    }
}
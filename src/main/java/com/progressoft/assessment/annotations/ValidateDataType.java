package com.progressoft.assessment.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidateDataType {
    DataType value();

    enum DataType {
        STRING,
        INTEGER,
        DOUBLE,
        BOOLEAN,
        BIG_DECIMAL

    }

}

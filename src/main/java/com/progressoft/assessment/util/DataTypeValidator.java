package com.progressoft.assessment.util;

import com.progressoft.assessment.annotations.ValidateDataType;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class DataTypeValidator {
    public static boolean validate(Object object) {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ValidateDataType.class)) {
                ValidateDataType annotation = field.getAnnotation(ValidateDataType.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (value != null) {
                        switch (annotation.value()) {
                            case STRING:
                                if (!(value instanceof String)) {
                                    return false;
                                }
                                break;
                            case INTEGER:
                                if (!(value instanceof Integer)) {
                                    return false;
                                }
                                break;
                            case DOUBLE:
                                if (!(value instanceof Double)) {
                                    return false;
                                }
                                break;
                            case BOOLEAN:
                                if (!(value instanceof Boolean)) {
                                    return false;
                                }
                                break;
                            case BIG_DECIMAL:
                                if(!(value instanceof BigDecimal)){
                                    return false;
                                }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}

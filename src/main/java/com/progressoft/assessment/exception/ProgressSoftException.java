package com.progressoft.assessment.exception;

public class ProgressSoftException extends RuntimeException{
    ProgressSoftException(String message){
        super(message);
    }

    ProgressSoftException(String message, Throwable cause){
        super(message, cause);
        if(this.getCause() == null && cause != null){
            this.initCause(cause);
        }
    }
}

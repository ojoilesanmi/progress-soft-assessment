package com.progressoft.assessment.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.progressoft.assessment.data.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private Status status;
    private String message;
    private String responseCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(Status status, String message, String responseCode){
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    public BaseResponse(Status status, String message){
        this.status = status;
        this.message = message;
    }
}


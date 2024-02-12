package com.progressoft.assessment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.progressoft.assessment.data.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseHandler {

    private String code;
    private String message;
    private ErrorCode error;


}

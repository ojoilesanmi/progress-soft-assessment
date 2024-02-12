package com.progressoft.assessment.dto.request;

import com.progressoft.assessment.annotations.ValidateDataType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data

public class FXDealsRequest {
    @ValidateDataType(ValidateDataType.DataType.STRING)
    private String dealUniqueId;
    @Size(min = 3, max = 3, message = "ISOCode cannot be less than or greater than 3")
    @NotBlank(message = "ISO Code cannot be blank or empty")
    @ValidateDataType(ValidateDataType.DataType.STRING)
    private String orderingCurrencyISOCode;
    @Size(min = 3, max = 3, message = "ISOCode cannot be less than or greater than 3")
    @NotBlank(message = "ISO Code cannot be blank or empty")
    @ValidateDataType(ValidateDataType.DataType.STRING)
    private String toCurrencyISOCode;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 12, fraction = 2)
    @ValidateDataType(ValidateDataType.DataType.BIG_DECIMAL)
    private BigDecimal amount;

}

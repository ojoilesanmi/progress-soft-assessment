package com.progressoft.assessment.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.progressoft.assessment.util.JacksonConfig;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FXDealsRequest {
    @JsonDeserialize(using = JacksonConfig.StrictStringDeserializer.class)
    @NotBlank(message = "dealUniqueId cannot be null or empty")
    private String dealUniqueId;
    @Size(min = 3, max = 3, message = "ISOCode cannot be less than or greater than 3")
    @NotBlank(message = "ISO Code cannot be blank or empty")
    @JsonDeserialize(using = JacksonConfig.StrictStringDeserializer.class)
    private String orderingCurrencyISOCode;
    @Size(min = 3, max = 3, message = "ISOCode cannot be less than or greater than 3")
    @NotBlank(message = "ISO Code cannot be blank or empty")
    @JsonDeserialize(using = JacksonConfig.StrictStringDeserializer.class)
    private String toCurrencyISOCode;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 12, fraction = 2)
    @JsonDeserialize(using = JacksonConfig.StrictBigDecimalDeserializer.class)
    private BigDecimal amount;
}






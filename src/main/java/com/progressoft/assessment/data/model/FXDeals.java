package com.progressoft.assessment.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FXDeals {
    @Id
    @GeneratedValue
    private Long id;
    private String uniqueDealId;
    private String orderingCurrencyISOCode;
    private String toCurrencyISOCode;
    private BigDecimal amount;
    private Date timeStamp;

    public FXDeals(String uniqueDealId, String orderingCurrencyISOCode, String toCurrencyISOCode, BigDecimal amount,
                   Date timeStamp){
        this.uniqueDealId = uniqueDealId;
        this.orderingCurrencyISOCode = orderingCurrencyISOCode;
        this.toCurrencyISOCode = toCurrencyISOCode;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

}

package com.progressoft.assessment.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class FXDeals {
    @Id
    @GeneratedValue
    private Long id;
    private String dealId;
    private String orderingCurrencyISOCode;
    private String toCurrencyISOCode;
    private BigDecimal amount;
    private Date timeStamp;

}

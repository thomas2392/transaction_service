package com.wex.transaction_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponseDTO {
    private Integer id;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal purchasedAmount;
    private double exchangeRate;
    private String countryCurrency;
    private BigDecimal convertedValue;
}

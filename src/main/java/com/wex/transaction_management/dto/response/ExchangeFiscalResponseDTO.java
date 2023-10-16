package com.wex.transaction_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeFiscalResponseDTO {
    private String country_currency_desc;
    private double exchange_rate;
    private LocalDate record_date;
}

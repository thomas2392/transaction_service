package com.wex.transaction_management.commons;

import com.wex.transaction_management.dto.response.ExchangeDataResponseDTO;
import com.wex.transaction_management.dto.response.ExchangeFiscalResponseDTO;

import java.time.LocalDate;
import java.util.Arrays;

public class ExchangeCreator {

    public static ExchangeDataResponseDTO createExchangeResponse(){
        return ExchangeDataResponseDTO.builder()
                .data(Arrays.asList(createExchange()))
                .build();
    }

    public static ExchangeFiscalResponseDTO createExchange(){
        return ExchangeFiscalResponseDTO.builder()
                .country_currency_desc("Brazil-Real")
                .exchange_rate(5.033)
                .record_date(LocalDate.now())
                .build();
    }
}

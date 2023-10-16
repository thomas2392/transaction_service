package com.wex.transaction_management.integration.fiscalData;

import com.wex.transaction_management.dto.response.ExchangeDataResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "fiscalData", url = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service")
public interface FiscalDataClient {

    @GetMapping(value = "/v1/accounting/od/rates_of_exchange")
    ExchangeDataResponseDTO getExchangeRate(@RequestParam Map<String,String> requestParams);
}

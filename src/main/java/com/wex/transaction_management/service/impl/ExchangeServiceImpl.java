package com.wex.transaction_management.service.impl;

import com.wex.transaction_management.dto.response.ExchangeDataResponseDTO;
import com.wex.transaction_management.dto.response.ExchangeFiscalResponseDTO;
import com.wex.transaction_management.dto.response.ExchangeResponseDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.integration.fiscalData.FiscalDataClient;
import com.wex.transaction_management.model.impl.Transaction;
import com.wex.transaction_management.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    FiscalDataClient fiscalDataClient;

    @Override
    public ExchangeResponseDTO convertTransactionToExchangeRate(Integer transactionId, String countryCurrency) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        ExchangeDataResponseDTO fiscalResponse = getFiscalDataExchange(countryCurrency, transaction);
        ExchangeFiscalResponseDTO dto = getExchangeFromLastSixMonths(fiscalResponse, transaction.getTransactionDate());
        double exchangeRate = dto.getExchange_rate();
        BigDecimal convertedValue = transaction.getPurchasedAmount().multiply(BigDecimal.valueOf(exchangeRate)).setScale(2, RoundingMode.HALF_UP);
        return ExchangeResponseDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .transactionDate(transaction.getTransactionDate())
                .purchasedAmount(transaction.getPurchasedAmount())
                .exchangeRate(exchangeRate)
                .countryCurrency(dto.getCountry_currency_desc())
                .convertedValue(convertedValue)
                .build();
    }

    private ExchangeDataResponseDTO getFiscalDataExchange(String countryCurrency, Transaction transaction){
        String country_currency_filter = "country_currency_desc:in:(" + countryCurrency + ")";
        String record_date_filter = ",record_fiscal_year:in:(" +transaction.getTransactionDate().getYear() + ")";
        String filter = country_currency_filter + record_date_filter;
        String sort = "-record_date";
        Map<String, String> params = new HashMap<>();
        params.put("filter", filter);
        params.put("sort", sort);
        log.info("Making request to fiscal API using filters: [" + filter + "]");
        try {
            return fiscalDataClient.getExchangeRate(params);
        } catch (Exception e){
            return null;
        }
    }

    private ExchangeFiscalResponseDTO getExchangeFromLastSixMonths(ExchangeDataResponseDTO fiscalResponse, LocalDate transactionDate){
        LocalDate lastSixMonths = transactionDate.minusMonths(6);
        Optional<ExchangeFiscalResponseDTO> optional = fiscalResponse.getData().stream()
                .filter(exchangeResponse -> exchangeResponse.getRecord_date().isAfter(lastSixMonths))
                .findFirst();
        if(optional.isEmpty()){
            throw new TransactionException(ErrorMessageEnum.EXCHANGE_NOT_FOUND);
        }
        return optional.get();
    }
}

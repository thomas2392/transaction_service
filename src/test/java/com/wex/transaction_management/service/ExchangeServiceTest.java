package com.wex.transaction_management.service;

import com.wex.transaction_management.commons.ExchangeCreator;
import com.wex.transaction_management.commons.TransactionCreator;
import com.wex.transaction_management.dto.response.ExchangeDataResponseDTO;
import com.wex.transaction_management.dto.response.ExchangeResponseDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.integration.fiscalData.FiscalDataClient;
import com.wex.transaction_management.service.impl.ExchangeServiceImpl;
import com.wex.transaction_management.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {

    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    @Mock
    TransactionServiceImpl transactionService;

    @Mock
    FiscalDataClient fiscalDataClient;

    @Test
    public void testConvertTransactionToExchangeRate(){
        String countryCurrency = "Brazil-Real";
        Integer transactionId = 1;
        when(transactionService.getTransaction(any())).thenReturn(TransactionCreator.createTransaction());
        when(fiscalDataClient.getExchangeRate(any())).thenReturn(ExchangeCreator.createExchangeResponse());
        ExchangeResponseDTO dto = exchangeService.convertTransactionToExchangeRate(transactionId, countryCurrency);
        assertNotNull(dto.getConvertedValue());
        System.out.println("Converted value: " + dto.getConvertedValue());
    }

    @Test
    public void testWhenNoExchangeRateFoundInSixMonthsShouldThrowException(){
        String expectedResult = ErrorMessageEnum.EXCHANGE_NOT_FOUND.getDescription();
        String countryCurrency = "Brazil-Real";
        Integer transactionId = 1;
        when(transactionService.getTransaction(any())).thenReturn(TransactionCreator.createTransaction());
        ExchangeDataResponseDTO dto = ExchangeCreator.createExchangeResponse();
        dto.getData().get(0).setRecord_date(LocalDate.of(1900, 1, 8));
        when(fiscalDataClient.getExchangeRate(any())).thenReturn(dto);
        TransactionException transactionException = assertThrows(
                TransactionException.class, () -> exchangeService.convertTransactionToExchangeRate(transactionId, countryCurrency));
        String result = transactionException.getDescription();
        assertEquals(expectedResult, result);
    }
}

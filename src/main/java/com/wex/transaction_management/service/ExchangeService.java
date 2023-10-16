package com.wex.transaction_management.service;

import com.wex.transaction_management.dto.response.ExchangeResponseDTO;

public interface ExchangeService {

    ExchangeResponseDTO convertTransactionToExchangeRate(Integer transactionId, String countryCurrency);
}

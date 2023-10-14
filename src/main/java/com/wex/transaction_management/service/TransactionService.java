package com.wex.transaction_management.service;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.dto.response.TransactionResponseDTO;

public interface TransactionService {

    TransactionResponseDTO saveTransaction(TransactionRequestDTO request);

}

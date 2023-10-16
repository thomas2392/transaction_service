package com.wex.transaction_management.commons;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionCreator {

    public static Transaction createTransaction(){
        return Transaction.builder()
                .id(1)
                .description("Test Transaction")
                .purchasedAmount(BigDecimal.valueOf(100))
                .transactionDate(LocalDate.now())
                .build();
    }

    public static TransactionRequestDTO createTransactionRequestDTO(){
        return TransactionRequestDTO.builder()
                .description("Test Transaction")
                .purchasedAmount(BigDecimal.valueOf(100))
                .transactionDate("2023-10-16")
                .build();
    }
}

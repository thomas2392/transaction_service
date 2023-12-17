package com.wex.transaction_management.commons;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.model.impl.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionCreator {

    public static Transaction createTransaction() {
        Integer id = 1;
        String description = "Test Transaction";
        BigDecimal amount = BigDecimal.valueOf(100);
        LocalDate date = LocalDate.now();

        return new Transaction(id, description, date, amount);
    }

    public static TransactionRequestDTO createTransactionRequestDTO(){
        String description = "Test Transaction";
        BigDecimal purchasedAmount = BigDecimal.valueOf(100);
        String transactionDate = "2023-10-16";

        return new TransactionRequestDTO(description, transactionDate, purchasedAmount);
    }
}

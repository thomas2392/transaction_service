package com.wex.transaction_management.model;

import java.math.BigDecimal;
import java.time.LocalDate;

// Public interface for transaction
public interface TransactionInterface {
    void setId(Integer id);
    Integer getId();
    void setDescription(String description);
    String getDescription();
    void setTransactionDate(LocalDate transactionDate);
    LocalDate getTransactionDate();
    void setPurchasedAmount(BigDecimal purchasedAmount);
    BigDecimal getPurchasedAmount();
}

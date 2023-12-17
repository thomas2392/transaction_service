package com.wex.transaction_management.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRequestDTO {

    @NotNull(message = "Please inform a description for the transaction.")
    private String description;
    private String transactionDate;
    @NotNull(message = "Please inform a value for the transaction.")
    private BigDecimal purchasedAmount;

    public TransactionRequestDTO() {}

    public TransactionRequestDTO(String description, String transactionDate, BigDecimal purchasedAmount) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.purchasedAmount = purchasedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(BigDecimal purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }
}

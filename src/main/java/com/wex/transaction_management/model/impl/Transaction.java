package com.wex.transaction_management.model.impl;

import com.wex.transaction_management.model.TransactionInterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

// Class representing a Transaction
public class Transaction implements TransactionInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal purchasedAmount;

    public Transaction() {}

    public Transaction(Integer id, String description, LocalDate transactionDate, BigDecimal purchasedAmount) {
        this.id = id;
        this.description = description;
        this.transactionDate = transactionDate;
        this.purchasedAmount = purchasedAmount;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @Override
    public void setPurchasedAmount(BigDecimal purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    @Override
    public BigDecimal getPurchasedAmount() {
        return purchasedAmount;
    }
}
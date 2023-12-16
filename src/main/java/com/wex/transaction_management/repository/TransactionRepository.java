package com.wex.transaction_management.repository;


import com.wex.transaction_management.model.impl.Transaction;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findById(Integer id);
    Transaction save(Transaction transaction);
}
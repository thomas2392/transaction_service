package com.wex.transaction_management.repository;

import com.wex.transaction_management.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

package com.wex.transaction_management.service.impl;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.model.Transaction;
import com.wex.transaction_management.repository.TransactionRepository;
import com.wex.transaction_management.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction getTransaction(Integer id) {
        log.info("Accessing database to get value of transaction. [transactionId={}]", id);
        Optional<Transaction> transaction = repository.findById(id);
        if(!transaction.isPresent()){
            log.info("Not transaction found. [transaction_id={}", id);
            return null;
        }
        return transaction.get();

    }

    @Override
    public Transaction saveTransaction(TransactionRequestDTO request) {
        Transaction transaction = Transaction.builder()
                .description(request.getDescription())
                .transactionDate(LocalDate.parse(request.getTransactionDate()))
                .purchasedAmount(request.getPurchasedAmount())
                .build();
        return repository.save(transaction);
    }
}

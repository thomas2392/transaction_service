package com.wex.transaction_management.service.impl;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.dto.response.TransactionResponseDTO;
import com.wex.transaction_management.model.Transaction;
import com.wex.transaction_management.repository.TransactionRepository;
import com.wex.transaction_management.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction getTransaction(Integer id) {
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
                .transactionDate(LocalDateTime.parse(request.getTransactionDate()))
                .purchasedAmount(request.getPurchasedAmount())
                .build();
        return repository.save(transaction);
    }
}

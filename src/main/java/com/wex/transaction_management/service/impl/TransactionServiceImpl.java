package com.wex.transaction_management.service.impl;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.model.Transaction;
import com.wex.transaction_management.repository.TransactionRepository;
import com.wex.transaction_management.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        if(request.getDescription().length() > 50){
            throw new TransactionException(ErrorMessageEnum.SIZE_VALIDATION_ERROR);
        }
        if(request.getPurchasedAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new TransactionException(ErrorMessageEnum.INVALID_VALUE);
        }
        try {
            Transaction transaction = Transaction.builder()
                    .description(request.getDescription())
                    .transactionDate(LocalDate.parse(request.getTransactionDate()))
                    .purchasedAmount(request.getPurchasedAmount().setScale(2, RoundingMode.HALF_UP))
                    .build();
            return repository.save(transaction);
        } catch(DateTimeParseException e){
            throw new TransactionException(ErrorMessageEnum.INVALID_DATE_FORMAT);
        }
    }
}

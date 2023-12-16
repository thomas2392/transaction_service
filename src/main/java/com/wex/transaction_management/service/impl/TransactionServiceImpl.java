package com.wex.transaction_management.service.impl;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.model.impl.Transaction;
import com.wex.transaction_management.repository.TransactionRepository;
import com.wex.transaction_management.service.TransactionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionServiceImpl implements TransactionService {

    private final Logger logger = Logger.getLogger(TransactionServiceImpl.class.getName());
    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction getTransaction(Integer id) {
        logger.log(Level.INFO, "Accessing database to get value of transaction. [transactionId={0}]", id);
        Optional<Transaction> transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new TransactionException(ErrorMessageEnum.TRANSACTION_NOT_FOUND);
        }
        return transaction.get();
    }

    @Override
    public Transaction saveTransaction(TransactionRequestDTO request) {
        validateTransactionRequest(request);

        logger.log(Level.INFO, "Accessing database to save transaction. [transaction={0}]", request);
        try {
            Transaction transaction = createTransactionFromRequest(request);
            transaction = repository.save(transaction);
            logger.log(Level.INFO, "Transaction saved successfully.");
            return transaction;
        } catch (DateTimeParseException e) {
            throw new TransactionException(ErrorMessageEnum.INVALID_DATE_FORMAT);
        }
    }

    private void validateTransactionRequest(TransactionRequestDTO request) {
        if (request.getDescription().length() > 50) {
            throw new TransactionException(ErrorMessageEnum.SIZE_VALIDATION_ERROR);
        }
        if (request.getPurchasedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException(ErrorMessageEnum.INVALID_VALUE);
        }
    }

    private Transaction createTransactionFromRequest(TransactionRequestDTO request) {
        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setTransactionDate(LocalDate.parse(request.getTransactionDate()));
        transaction.setPurchasedAmount(request.getPurchasedAmount().setScale(2, RoundingMode.HALF_UP));
        return transaction;
    }
}

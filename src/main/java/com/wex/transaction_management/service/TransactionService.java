package com.wex.transaction_management.service;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.model.impl.Transaction;

public interface TransactionService {

    Transaction getTransaction(Integer id);
    Transaction saveTransaction(TransactionRequestDTO transaction);

}

package com.wex.transaction_management.controllers;

import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.model.Transaction;
import com.wex.transaction_management.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> getTransaction(@PathVariable Integer id){
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> saveNewTransaction(@RequestBody @Valid TransactionRequestDTO requestDTO) throws Exception{
        Transaction transaction = transactionService.saveTransaction(requestDTO);
        return ResponseEntity.ok(transaction);
    }
}

package com.wex.transaction_management.controllers;

import com.wex.transaction_management.dto.response.ExchangeResponseDTO;
import com.wex.transaction_management.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/exchange")
@AllArgsConstructor
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @GetMapping(value = "/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeResponseDTO> convertTransactionToExchangeRate(@PathVariable Integer transactionId,
      @RequestBody @Valid String countryCurrency){
        ExchangeResponseDTO response = exchangeService.convertTransactionToExchangeRate(transactionId, countryCurrency);
        return ResponseEntity.ok(response);
    }
}

package com.wex.transaction_management.service;

import com.wex.transaction_management.commons.TransactionCreator;
import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.model.Transaction;
import com.wex.transaction_management.repository.TransactionRepository;
import com.wex.transaction_management.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository repository;

    @Test
    public void testWhenExceedCharactersInDescriptionOnSave(){
        String expectedResult = ErrorMessageEnum.SIZE_VALIDATION_ERROR.getDescription();
        TransactionRequestDTO transactionRequestDTO = TransactionCreator.createTransactionRequestDTO();
        String longCharacters = "123456789012345678901234567890123456789012345678901";
        transactionRequestDTO.setDescription(longCharacters);
        TransactionException transactionException = assertThrows(
                TransactionException.class, () -> transactionService.saveTransaction(transactionRequestDTO));
        String result = transactionException.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWhenInvalidDateFormatOnSaveShouldThrowException(){
        String expectedResult = ErrorMessageEnum.INVALID_DATE_FORMAT.getDescription();
        TransactionRequestDTO transactionRequestDTO = TransactionCreator.createTransactionRequestDTO();
        transactionRequestDTO.setTransactionDate("01/01/1900");
        TransactionException transactionException = assertThrows(
                TransactionException.class, () -> transactionService.saveTransaction(transactionRequestDTO));
        String result = transactionException.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWhenInvalidValueOnSaveShouldThrowException(){
        String expectedResult = ErrorMessageEnum.INVALID_VALUE.getDescription();
        TransactionRequestDTO transactionRequestDTO = TransactionCreator.createTransactionRequestDTO();
        transactionRequestDTO.setPurchasedAmount(BigDecimal.ZERO);
        TransactionException transactionException = assertThrows(
                TransactionException.class, () -> transactionService.saveTransaction(transactionRequestDTO));
        String result = transactionException.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWhenTransactionNotFoundOnGetShouldThrowException(){
        String expectedResult = ErrorMessageEnum.TRANSACTION_NOT_FOUND.getDescription();
        TransactionException transactionException = assertThrows(
                TransactionException.class, () -> transactionService.getTransaction(0));
        String result = transactionException.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWhenValidOnSaveSuccessfully(){
        when(repository.save(any())).thenReturn(TransactionCreator.createTransaction());
        Transaction transaction = transactionService.saveTransaction(TransactionCreator.createTransactionRequestDTO());
        assertNotNull(transaction);
    }

    @Test
    public void testWhenTransactionFoundOnGetShouldReturnSuccessfully(){
        when(repository.findById(any())).thenReturn(Optional.of(TransactionCreator.createTransaction()));
        Transaction transaction = transactionService.getTransaction(1);
        assertNotNull(transaction);
    }

}

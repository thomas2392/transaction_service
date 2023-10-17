package com.wex.transaction_management.service;

import com.wex.transaction_management.commons.TransactionCreator;
import com.wex.transaction_management.dto.request.TransactionRequestDTO;
import com.wex.transaction_management.exception.ErrorMessageEnum;
import com.wex.transaction_management.exception.TransactionException;
import com.wex.transaction_management.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

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
}

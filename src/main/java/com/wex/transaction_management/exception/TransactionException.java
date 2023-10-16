package com.wex.transaction_management.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TransactionException extends RuntimeException {

    private String errorCode;
    private String description;

    public TransactionException (String description){this.description = description;}

    public TransactionException(ErrorMessageEnum error){
        this.errorCode = error.getErrorCode();
        this.description = error.getDescription();
    }

    public static TransactionException with(ErrorMessageEnum error){return new TransactionException(error);}
}

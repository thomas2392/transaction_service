package com.wex.transaction_management.exception;

import lombok.Getter;

import java.util.Arrays;

public enum ErrorMessageEnum {

    SIZE_VALIDATION_ERROR("v-01", "Too many characters.");

    @Getter
    final String errorCode;
    @Getter
    final String description;

    ErrorMessageEnum(String errorCode, String description){
        this.errorCode = errorCode;
        this.description = description;
    }

    public static ErrorMessageEnum toEnum(String errorCode){
        return Arrays.stream(ErrorMessageEnum.values()).filter(field -> field.getErrorCode().equals(errorCode)).findFirst()
                .orElseThrow(() -> new TransactionException("Message not found."));
    }
}

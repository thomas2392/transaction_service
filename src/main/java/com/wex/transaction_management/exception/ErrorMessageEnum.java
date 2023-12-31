package com.wex.transaction_management.exception;

import lombok.Getter;

import java.util.Arrays;

public enum ErrorMessageEnum {

    SIZE_VALIDATION_ERROR("v-01", "Too many characters."),
    INVALID_DATE_FORMAT("v-02", "Invalid date format, please inform a date in format yyyy-MM-dd."),
    INVALID_VALUE("v-03", "Invalid purchase value, please inform a valid positive amount."),
    TRANSACTION_NOT_FOUND("v-04", "Transaction not found on database."),
    EXCHANGE_NOT_FOUND("v-05", "The purchase cannot be converted to the target currency, no exchange rate found in last six months.");

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

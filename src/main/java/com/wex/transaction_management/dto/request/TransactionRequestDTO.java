package com.wex.transaction_management.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRequestDTO {

    @NotNull(message = "please inform a description for the transaction")
    private String description;
    private String transactionDate;
    @NotNull(message = "please inform a value for the transaction")
    private BigDecimal purchasedAmount;

}

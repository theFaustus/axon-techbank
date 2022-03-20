package com.evil.inc.account.query.api.web.dto;

import com.evil.inc.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String id;
    private String accountHolder;
    private String accountType;
    private double balance;

    public static AccountResponse from(BankAccount bankAccount){
        return AccountResponse.builder()
                .id(bankAccount.getId())
                .accountHolder(bankAccount.getAccountHolder())
                .accountType(bankAccount.getAccountType().name())
                .balance(bankAccount.getBalance())
                .build();
    }
}

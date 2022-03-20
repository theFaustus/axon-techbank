package com.evil.inc.account.common.events;

import com.evil.inc.account.common.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountOpenedEvent extends Event {
    private String accountHolder;
    private AccountType accountType;
    private LocalDateTime creationDate;
    private double openingBalance;
}

package com.evil.inc.account.command.api.commands;

import com.evil.inc.account.common.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpenAccountCommand extends Command {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}

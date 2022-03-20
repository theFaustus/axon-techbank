package com.evil.inc.account.command.api.commands;

import com.evil.inc.account.common.dto.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class OpenAccountCommand extends Command {
    private final String accountHolder;
    private final AccountType accountType;
    private final double openingBalance;
}

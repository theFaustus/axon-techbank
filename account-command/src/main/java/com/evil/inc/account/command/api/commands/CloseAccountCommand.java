package com.evil.inc.account.command.api.commands;

import com.evil.inc.cqrs.core.commands.Command;
import com.evil.inc.cqrs.core.domain.AggregateId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CloseAccountCommand extends Command {
    private String comment;
}

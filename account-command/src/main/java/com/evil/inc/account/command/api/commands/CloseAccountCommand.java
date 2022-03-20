package com.evil.inc.account.command.api.commands;

import com.evil.inc.cqrs.core.domain.AggregateId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CloseAccountCommand extends Command {
    private final String comment;
}

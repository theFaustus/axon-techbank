package com.evil.inc.account.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Command {

    @TargetAggregateIdentifier
    private String id;
    private final LocalDateTime systemCaptureDateTime = LocalDateTime.now();

}

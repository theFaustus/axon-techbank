package com.evil.inc.account.command.domain;

import com.evil.inc.account.command.api.commands.CloseAccountCommand;
import com.evil.inc.account.command.api.commands.DepositFundsCommand;
import com.evil.inc.account.command.api.commands.OpenAccountCommand;
import com.evil.inc.account.command.api.commands.WithdrawFundsCommand;
import com.evil.inc.account.common.events.AccountOpenedEvent;
import com.evil.inc.account.common.events.AccountClosedEvent;
import com.evil.inc.account.common.events.FundsDepositedEvent;
import com.evil.inc.account.common.events.FundsWithdrawnEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

@Data
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @AggregateIdentifier
    private String id;
    private boolean active;
    private double balance;

    @CommandHandler
    public Account(OpenAccountCommand command) {
        final AccountOpenedEvent event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .creationDate(LocalDateTime.now())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @CommandHandler
    public void on(DepositFundsCommand command) {
        if (!active) {
            throw new IllegalStateException("Funds cannot be deposited into a closed account!");
        }
        if (command.getAmount() <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than 0!");
        }

        final FundsDepositedEvent event = FundsDepositedEvent.builder()
                .id(this.id)
                .amount(command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void on(WithdrawFundsCommand command) {
        if (!active) {
            throw new IllegalStateException("Funds cannot be withdrawn from a closed account!");
        }
        if (command.getAmount() > balance) {
            throw new IllegalStateException("Funds cannot be withdrawn. Insufficient funds on the balance!");
        }

        final FundsWithdrawnEvent event = FundsWithdrawnEvent.builder()
                .id(this.id)
                .amount(command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void on(CloseAccountCommand command) {
        if (!active) {
            throw new IllegalStateException("Cannot close an already closed account!");
        }
        final AccountClosedEvent event = AccountClosedEvent.builder().id(this.id).build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    @EventSourcingHandler
    public void handle(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    @EventSourcingHandler
    public void handle(FundsWithdrawnEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    @EventSourcingHandler
    public void handle(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }

}

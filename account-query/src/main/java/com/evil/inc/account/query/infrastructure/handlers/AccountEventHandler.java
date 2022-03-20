package com.evil.inc.account.query.infrastructure.handlers;

import com.evil.inc.account.common.events.AccountClosedEvent;
import com.evil.inc.account.common.events.AccountOpenedEvent;
import com.evil.inc.account.common.events.FundsDepositedEvent;
import com.evil.inc.account.common.events.FundsWithdrawnEvent;
import com.evil.inc.account.query.infrastructure.AccountRepository;
import com.evil.inc.account.query.domain.BankAccount;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@ProcessingGroup("account-group")
class AccountEventHandler implements EventHandler {

    private final AccountRepository accountRepository;

    @Override
    @org.axonframework.eventhandling.EventHandler
    public void on(AccountOpenedEvent event) {
        final BankAccount bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(bankAccount);
    }

    @Override
    @org.axonframework.eventhandling.EventHandler
    public void on(FundsDepositedEvent event) {
        accountRepository.findById(event.getId())
                .ifPresent(bankAccount -> updateBalance(bankAccount, bankAccount.getBalance() + event.getAmount()));
    }


    @Override
    @org.axonframework.eventhandling.EventHandler
    public void on(FundsWithdrawnEvent event) {
        accountRepository.findById(event.getId())
                .ifPresent(bankAccount -> updateBalance(bankAccount, bankAccount.getBalance() - event.getAmount()));
    }

    private void updateBalance(BankAccount bankAccount, double balance) {
        bankAccount.setBalance(balance);
        accountRepository.save(bankAccount);
    }

    @Override
    @org.axonframework.eventhandling.EventHandler
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}

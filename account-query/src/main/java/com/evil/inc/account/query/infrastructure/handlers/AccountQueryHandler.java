package com.evil.inc.account.query.infrastructure.handlers;

import com.evil.inc.account.query.api.queries.FindAccountByAccountHolderQuery;
import com.evil.inc.account.query.api.queries.FindAccountByIdQuery;
import com.evil.inc.account.query.api.queries.FindAllAccountsQuery;
import com.evil.inc.account.query.domain.BankAccount;
import com.evil.inc.account.query.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AccountQueryHandler implements QueryHandler {

    private final AccountRepository accountRepository;

    @Override
    public List<BankAccount> handle(FindAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    //bad, very bad, using collection instead of Optional, even more there should be a DTO
    @Override
    public List<BankAccount> handle(FindAccountByIdQuery query) {
        final Optional<BankAccount> byId = accountRepository.findById(query.getId());
        if (byId.isEmpty()) {
            return Collections.emptyList();
        } else {
            return List.of(byId.get());
        }
    }

    @Override
    public List<BankAccount> handle(FindAccountByAccountHolderQuery query) {
        final Optional<BankAccount> byAccountHolder = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (byAccountHolder.isEmpty()) {
            return Collections.emptyList();
        } else {
            return List.of(byAccountHolder.get());
        }
    }
}

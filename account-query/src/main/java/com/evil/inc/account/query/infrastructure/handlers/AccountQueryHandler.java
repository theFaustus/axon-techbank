package com.evil.inc.account.query.infrastructure.handlers;

import com.evil.inc.account.query.api.web.dto.AccountResponse;
import com.evil.inc.account.query.api.queries.FindAccountByAccountHolderQuery;
import com.evil.inc.account.query.api.queries.FindAccountByIdQuery;
import com.evil.inc.account.query.api.queries.FindAllAccountsQuery;
import com.evil.inc.account.query.domain.BankAccount;
import com.evil.inc.account.query.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AccountQueryHandler implements QueryHandler {

    private final AccountRepository accountRepository;

    @Override
    @org.axonframework.queryhandling.QueryHandler
    public List<AccountResponse> handle(FindAllAccountsQuery query) {
        return accountRepository.findAll().stream().map(AccountResponse::from).collect(Collectors.toList());
    }

    @Override
    @org.axonframework.queryhandling.QueryHandler
    public AccountResponse handle(FindAccountByIdQuery query) {
        final BankAccount bankAccount = accountRepository.findById(query.getId()).orElseThrow();
        return AccountResponse.from(bankAccount);
    }

    @Override
    @org.axonframework.queryhandling.QueryHandler
    public AccountResponse handle(FindAccountByAccountHolderQuery query) {
        final BankAccount bankAccount = accountRepository.findByAccountHolder(query.getAccountHolder()).orElseThrow();
        return AccountResponse.from(bankAccount);

    }
}

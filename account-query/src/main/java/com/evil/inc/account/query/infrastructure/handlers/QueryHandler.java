package com.evil.inc.account.query.infrastructure.handlers;

import com.evil.inc.account.query.api.web.dto.AccountResponse;
import com.evil.inc.account.query.api.queries.FindAccountByAccountHolderQuery;
import com.evil.inc.account.query.api.queries.FindAccountByIdQuery;
import com.evil.inc.account.query.api.queries.FindAllAccountsQuery;

import java.util.List;

public interface QueryHandler {
    List<AccountResponse> handle(FindAllAccountsQuery query);
    AccountResponse handle(FindAccountByIdQuery query);
    AccountResponse handle(FindAccountByAccountHolderQuery query);
}

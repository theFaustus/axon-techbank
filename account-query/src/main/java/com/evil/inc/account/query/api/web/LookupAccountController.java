package com.evil.inc.account.query.api.web;

import com.evil.inc.account.query.api.web.dto.AccountResponse;
import com.evil.inc.account.query.api.queries.FindAccountByAccountHolderQuery;
import com.evil.inc.account.query.api.queries.FindAccountByIdQuery;
import com.evil.inc.account.query.api.queries.FindAllAccountsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class LookupAccountController {

    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        final List<AccountResponse> responses = queryGateway.query(new FindAllAccountsQuery(), ResponseTypes.multipleInstancesOf(AccountResponse.class)).join();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAllAccountsById(@PathVariable String id){
        final AccountResponse response = queryGateway.query(new FindAccountByIdQuery(id), ResponseTypes.instanceOf(AccountResponse.class)).join();
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "accountHolder")
    public ResponseEntity<AccountResponse> getAllAccountsByAccountHolder(@RequestParam(value = "accountHolder") String accountHolder){
        final AccountResponse response = queryGateway.query(new FindAccountByAccountHolderQuery(accountHolder), ResponseTypes.instanceOf(AccountResponse.class)).join();
        return ResponseEntity.ok(response);
    }
}

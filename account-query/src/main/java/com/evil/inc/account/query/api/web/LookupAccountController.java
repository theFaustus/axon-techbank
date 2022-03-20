package com.evil.inc.account.query.api.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class LookupAccountController {

//    private final QueryGateway queryGateway;
//
//    @GetMapping
//    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
//        final List<BankAccount> baseEntities = queryGateway.query(new FindAllAccountsQuery());
//        final List<AccountResponse> response = baseEntities.stream().map(AccountResponse::from).collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<AccountResponse>> getAllAccountsById(@PathVariable String id){
//        final List<BankAccount> baseEntities = queryGateway.send(new FindAccountByIdQuery(id));
//        final List<AccountResponse> response = baseEntities.stream().map(AccountResponse::from).collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping(params = "accountHolder")
//    public ResponseEntity<List<AccountResponse>> getAllAccountsByAccountHolder(@RequestParam(value = "accountHolder") String accountHolder){
//        final List<BankAccount> baseEntities = queryGateway.send(new FindAccountByAccountHolderQuery(accountHolder));
//        final List<AccountResponse> response = baseEntities.stream().map(AccountResponse::from).collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
}

package com.evil.inc.account.command.api.web;

import com.evil.inc.account.command.api.commands.CloseAccountCommand;
import com.evil.inc.account.command.api.commands.DepositFundsCommand;
import com.evil.inc.account.command.api.commands.OpenAccountCommand;
import com.evil.inc.account.command.api.commands.RestoreDbCommand;
import com.evil.inc.account.command.api.commands.WithdrawFundsCommand;
import com.evil.inc.account.command.api.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<Response> openAccount(@RequestBody OpenAccountCommand command){
        command.setId(UUID.randomUUID().toString());
        commandGateway.send(command);
        return ResponseEntity.ok(new Response("Bank account [" + command.getId() + "] created"));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> depositFunds(@RequestBody DepositFundsCommand command){
        commandGateway.send(command);
        return ResponseEntity.ok(new Response("Successfully deposited " + command.getAmount() + " to account [" + command.getId() + "]"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Response> withdrawFunds(@RequestBody WithdrawFundsCommand command){
        commandGateway.send(command);
        return ResponseEntity.ok(new Response("Successfully withdrawn " + command.getAmount() + " from account [" + command.getId() + "]"));
    }

    @PostMapping("/republish")
    public ResponseEntity<Response> republishEvents(){
        commandGateway.send(RestoreDbCommand.builder().build());
        return ResponseEntity.ok(new Response("Restore of the DB started successfully"));
    }

    @DeleteMapping
    public ResponseEntity<Response> closeAccount(@RequestBody CloseAccountCommand command){
        commandGateway.send(command);
        return ResponseEntity.ok(new Response("Bank account [" + command.getId() + "] closed"));
    }

}

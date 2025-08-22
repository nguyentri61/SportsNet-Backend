package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.account.AccountRegisterRequest;
import com.tlcn.sportsnet_backend.dto.account.AccountResponse;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountRegisterRequest request) {
        AccountResponse res = accountService.registerAccount(request);
        return ResponseEntity.ok(res);
    }

}

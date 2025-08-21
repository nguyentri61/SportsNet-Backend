package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.util.AccountDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailsCustom(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountService.findByEmail(email);

        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        return new AccountDetails(account.get());
    }
}

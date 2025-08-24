package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.account.AccountRegisterRequest;
import com.tlcn.sportsnet_backend.dto.account.AccountResponse;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Role;
import com.tlcn.sportsnet_backend.entity.UserInfo;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.RoleRepository;
import com.tlcn.sportsnet_backend.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account registerAccount(AccountRegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại, vui lòng chọn email khác.");
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));

        Account newAccount = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        UserInfo userInfo = UserInfo.builder()
                .fullName(request.getFullName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .address(request.getAddress())
                .account(newAccount)
                .build();
        

        newAccount.setUserInfo(userInfo);
        newAccount = accountRepository.save(newAccount);

        return newAccount;

    }

    public static AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .enabled(account.isEnabled())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .createdBy(account.getCreatedBy())
                .updatedBy(account.getUpdatedBy())
                .userInfo(account.getUserInfo() != null
                        ? AccountResponse.UserInfoRes.builder()
                        .fullName(account.getUserInfo().getFullName())
                        .birthDate(account.getUserInfo().getBirthDate())
                        .gender(account.getUserInfo().getGender())
                        .address(account.getUserInfo().getAddress())
                        .build()
                        : null
                )
                .build();
    }
}

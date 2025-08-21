package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.AccountDTO;
import com.tlcn.sportsnet_backend.dto.UserInfoDTO;
import com.tlcn.sportsnet_backend.dto.auth.AccountRegisterRequest;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Role;
import com.tlcn.sportsnet_backend.entity.UserInfo;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Account account) {
        accountRepository.save(account);
    }

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

        return accountRepository.save(newAccount);
    }

//    public static AccountDTO toDTO(Account account) {
//        return AccountDTO.builder()
//                .id(account.getId())
//                .email(account.getEmail())
//                .enabled(account.isEnabled())
//                .userInfo(toUserInfoDTO(account.getUserInfo()))
//                .roles(account.getRoles()
//                        .stream()
//                        .map(role -> role.getName())
//                        .collect(Collectors.toSet()))
//                .build();
//    }
//
//    private static UserInfoDTO toUserInfoDTO(UserInfo userInfo) {
//        if (userInfo == null) return null;
//
//        return UserInfoDTO.builder()
//                .fullName(userInfo.getFullName())
//                .birthDate(userInfo.getBirthDate())
//                .gender(userInfo.getGender())
//                .address(userInfo.getAddress())
//                .bio(userInfo.getBio())
//                .avatarUrl(userInfo.getAvatarUrl())
//                .build();
//    }
}

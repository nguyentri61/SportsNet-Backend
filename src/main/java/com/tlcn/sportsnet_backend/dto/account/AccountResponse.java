package com.tlcn.sportsnet_backend.dto.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String email;
    UserInfoRes userInfo;
    boolean enabled;
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserInfoRes{
        String fullName;
        LocalDate birthDate;
        String gender;
        String address;
    }
}

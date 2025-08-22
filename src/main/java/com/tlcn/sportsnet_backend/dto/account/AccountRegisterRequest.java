package com.tlcn.sportsnet_backend.dto.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRegisterRequest {
    String email;
    String password;

    String fullName;
    LocalDate birthDate;
    String gender;
    String address;
}

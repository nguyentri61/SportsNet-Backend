package com.tlcn.sportsnet_backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDTO {
    String fullName;
    LocalDate birthDate;
    String gender;
    String address;
    String bio;
    String avatarUrl;
}

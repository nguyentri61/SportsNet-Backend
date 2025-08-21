package com.tlcn.sportsnet_backend.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTO {
    String email;
    String password;
}
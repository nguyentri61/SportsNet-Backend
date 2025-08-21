package com.tlcn.sportsnet_backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {
    String id;
    String email;
    boolean enabled;

    UserInfoDTO userInfo;
    Set<String> roles; // chỉ lấy tên role thay vì cả entity
}

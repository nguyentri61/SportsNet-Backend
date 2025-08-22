package com.tlcn.sportsnet_backend.dto.club;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubCreateRequest {
    String name;
    String description;
    String logoUrl;
    String ownerId;
}

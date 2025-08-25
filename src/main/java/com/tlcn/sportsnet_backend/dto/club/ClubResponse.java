package com.tlcn.sportsnet_backend.dto.club;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubResponse {
    String id;
    String name;
    String description;
    String logoUrl;
    boolean active;
    String ownerId;
    Instant createdAt;
}

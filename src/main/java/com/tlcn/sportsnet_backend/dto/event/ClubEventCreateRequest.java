package com.tlcn.sportsnet_backend.dto.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubEventCreateRequest {
    String title;
    String description;
    String location;
    LocalDateTime startTime;
    LocalDateTime endTime;
    int capacity;
    BigDecimal fee;

    String coverImage;
    String images;

    String facilityId;
    String clubId;
}

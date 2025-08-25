package com.tlcn.sportsnet_backend.dto.event;

import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.enums.EventVisibilityEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubEventResponse {
    String id;
    String title;
    String description;
    String location;
    LocalDateTime startTime;
    LocalDateTime endTime;

    int capacity;
    int currentParticipants; // số người đã tham gia
    BigDecimal fee;

    EventStatusEnum status;
    EventVisibilityEnum visibility;

    String clubId;
    String facilityId;
    String organizerId;
}

package com.tlcn.sportsnet_backend.dto.event;

import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
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
public class EventResponse {
    String id;
    String title;
    String description;
    String coverImageUrl;
    String location;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer capacity;
    BigDecimal fee;
    boolean recurring;
    String recurrenceRule;
    EventTypeEnum type;
    EventStatusEnum status;

    // thông tin liên kết
    String sportName;
    String clubName;
    String organizerName;

    Instant createdAt;
    Instant updatedAt;
}

package com.tlcn.sportsnet_backend.dto.event;

import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateRequest {
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

    String sportId;
    String clubId; // optional
}

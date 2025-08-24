package com.tlcn.sportsnet_backend.dto.event;

import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import com.tlcn.sportsnet_backend.enums.SportTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


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
    String images;
    String location;

    LocalDateTime startTime;
    LocalDateTime endTime;

    Integer capacity;
    BigDecimal fee;

    boolean recurring;
    String recurrenceRule;

    EventTypeEnum eventType;
    Map<String, Object> eventFormat;

    SportTypeEnum sportType;
    Map<String, Object> sportRule;

    EventStatusEnum status;

    String clubId;
    String organizerId;

    Instant createdAt;
    Instant updatedAt;
}

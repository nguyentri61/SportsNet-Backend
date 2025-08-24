package com.tlcn.sportsnet_backend.dto.event;
import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import com.tlcn.sportsnet_backend.enums.SportTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateRequest {
    String title;
    String description;

    MultipartFile coverImage;
    MultipartFile[] images;

    String location;

    LocalDateTime startTime;
    LocalDateTime endTime;

    Integer capacity;
    BigDecimal fee;

    boolean recurring;
    String recurrenceRule;

    String clubId; // nếu có

    EventTypeEnum eventType;
    Map<String, Object> eventFormat;

    SportTypeEnum sportType;
    Map<String, Object> sportRule;
}

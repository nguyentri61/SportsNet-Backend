package com.tlcn.sportsnet_backend.dto.sport;

import com.tlcn.sportsnet_backend.enums.SportTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SportResponse {
    String id;
    String name;
    SportTypeEnum type;
    Map<String, Object> defaultRules;
}
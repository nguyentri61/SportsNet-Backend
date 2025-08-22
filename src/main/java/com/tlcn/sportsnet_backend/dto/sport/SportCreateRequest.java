package com.tlcn.sportsnet_backend.dto.sport;

import com.tlcn.sportsnet_backend.enums.SportTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SportCreateRequest {
    String name;
    SportTypeEnum type;
    String defaultRules;
}

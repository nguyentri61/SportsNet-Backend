package com.tlcn.sportsnet_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadmintonSportRule {
    private Integer bestOf; // 3 (Bo3) hoặc 5 (Bo5)
    private Integer pointsPerSet; // 21
    private Integer winBy; // thắng cách biệt 2 điểm
    private Integer maxPoints; // tối đa 30
}
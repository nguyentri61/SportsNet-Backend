package com.tlcn.sportsnet_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadmintonEventFormat {
    private String mode; // "Knockout", "Round Robin", "Group+KO", ...
    private Integer rounds; // Số vòng (nếu Knockout)
    private Boolean thirdPlaceMatch;// Có tranh hạng ba không
}
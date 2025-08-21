package com.tlcn.sportsnet_backend.enums;

public enum EventTypeEnum {
    FRIENDLY_MATCH("Trận giao hữu"),
    TOURNAMENT("Giải đấu"),
    TRAINING("Buổi tập luyện"),
    COMPETITION("Thi đấu chính thức"),
    WORKSHOP("Workshop / Hướng dẫn kỹ năng"),
    COMMUNITY_EVENT("Sự kiện cộng đồng");

    private final String description;

    EventTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}
package com.tlcn.sportsnet_backend.enums;

public enum NotificationTypeEnum {
    DIRECT("Trực tiếp"),
    ONLINE("Trực tuyến"),
    NONE ("Khác");

    private final String description;

    NotificationTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

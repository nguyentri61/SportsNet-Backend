package com.tlcn.sportsnet_backend.enums;

public enum EventTypeEnum {
    TOURNAMENT("Giải đấu"),
    TRAINING_CLASS("Lớp học / Huấn luyện"),
    CLUB_ACTIVITY("Giao lưu CLB / Tuyển thành viên");

    private final String description;

    EventTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}
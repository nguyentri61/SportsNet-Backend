package com.tlcn.sportsnet_backend.enums;

public enum FriendStatusEnum {
    PENDING("Chờ xác nhận"),
    ACCEPTED("Chấp nhận"),
    BLOCKED("Chặn");

    private final String description;

    FriendStatusEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

package com.tlcn.sportsnet_backend.enums;

public enum NotificationTypeEnum {
    EVENT_INVITE("Lời mời tham gia sự kiện"),
    COMMENT("Bình luận"),
    LIKE("Yêu thích"),
    FRIEND_REQUEST("Yêu cầu bạn bè");

    private final String description;

    NotificationTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

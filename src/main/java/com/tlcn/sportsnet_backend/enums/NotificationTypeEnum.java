package com.tlcn.sportsnet_backend.enums;

public enum NotificationTypeEnum {
    EVENT_INVITE("Lời mời tham gia sự kiện"),
    COMMENT("Bình luận"),
    LIKE("Yêu thích"),
    FRIEND_REQUEST("Yêu cầu bạn bè"),
    ANNOUNCEMENT("Thông báo"), REMINDER("Lời nhắc"), RESULT("Kết quả"), CHANGE_SCHEDULE("Thay đổi lịch");

    private final String description;

    NotificationTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

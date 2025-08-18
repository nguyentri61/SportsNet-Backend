package com.tlcn.sportsnet_backend.enums;

public enum PaymentMethodEnum {
    EVENT_INVITE("Lời mời tham gia sự kiện"),
    COMMENT("Bình luận"),
    LIKE("Yêu thích"),
    FRIEND_REQUEST("Yêu cầu bạn bè");

    private final String description;

    PaymentMethodEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

package com.tlcn.sportsnet_backend.enums;

public enum SportTypeEnum {
    BADMINTON("Cầu lông"),
    FOOTBALL("Bóng đá"),
    BASKETBALL("Bóng rổ"),
    TENNIS("Tennis"),
    TABLE_TENNIS("Bóng bàn"),
    VOLLEYBALL("Bóng chuyền"),
    SWIMMING("Bơi lội"),
    RUNNING("Chạy bộ"),
    CYCLING("Đạp xe"),
    GYM("Gym / Thể hình"),
    YOGA("Yoga"),
    BOXING("Boxing"),
    MUAY_THAI("Muay Thái"),
    MARTIAL_ARTS("Võ thuật"),
    BASEBALL("Bóng chày"),
    RUGBY("Bóng bầu dục"),
    PICKLEBALL("Pickleball");

    private final String description;
    SportTypeEnum(String des) { this.description = des; }
    public String getDescription() { return description; }
}

package com.tlcn.sportsnet_backend.enums;

public enum ChatGroupTypeEnum {
    PRIVATE("Riêng tư"),
    GROUP("Nhóm"),
    CLUB("Câu lạc bộ");

    private final String description;

    ChatGroupTypeEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

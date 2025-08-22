package com.tlcn.sportsnet_backend.enums;

public enum SportTypeEnum {
    INDIVIDUAL("Cá nhân"),
    TEAM("Đội nhóm");

    private final String description;
    SportTypeEnum(String des) { this.description = des; }
    public String getDescription() { return description; }
}

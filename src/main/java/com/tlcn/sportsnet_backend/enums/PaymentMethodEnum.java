package com.tlcn.sportsnet_backend.enums;

public enum PaymentMethodEnum {
    DIRECT("Trực tiếp"),
    ONLINE("Trực tuyến"),
    NONE ("Khác");

    private final String description;

    PaymentMethodEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

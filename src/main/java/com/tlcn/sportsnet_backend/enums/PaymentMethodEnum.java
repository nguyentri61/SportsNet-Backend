package com.tlcn.sportsnet_backend.enums;

public enum PaymentMethodEnum {
    DIRECT("Trực tiếp"),
    BANKING("Chuyển khoản");

    private final String description;

    PaymentMethodEnum(String des) {
        this.description = des;
    }

    public String getDes() {
        return description;
    }
}

package com.tinqinacademy.bff.api.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BathroomType {
    PRIVATE("private"),
    SHARED("shared"),
    UNKNOWN("");

    private final String code;

    BathroomType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static BathroomType getFromCode(String code) {
        for (BathroomType type : BathroomType.values()) {
            if (type.getCode().equals(code)) {
                return type;

            }
        }
        return BathroomType.UNKNOWN;
    }

    @Override
    @JsonValue
    public String toString() {
        return getCode();
    }
}

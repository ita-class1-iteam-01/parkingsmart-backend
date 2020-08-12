package com.oocl.parkingsmart.Enum;

import lombok.Getter;

@Getter
public enum  RentOrderEnum {
    PROCESSING("PROCESSING"),
    FINISHED("FINISHED"),
    BOOKED("BOOKED"),
    PUBLISHED("PUBLISHED")
    ;
    String value;
    RentOrderEnum(String value) {
        this.value = value;
    }
}

package com.oocl.parkingsmart.Enum;

import lombok.Getter;

@Getter
public enum  RentOrderEnum {
    PROCESSING("processing"),
    FINISHED("finished"),
    BOOKED("booked"),
    PUBLISHED("published")
    ;
    String value;
    RentOrderEnum(String value) {
        this.value = value;
    }
}
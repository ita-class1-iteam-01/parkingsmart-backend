package com.oocl.parkingsmart.Enum;

import lombok.Getter;

@Getter
public enum  ParkingLotTypeEnum {
    LOTS("lots"),
    PERSONAL("personal");
    String value;
    ParkingLotTypeEnum(String value) {
        this.value = value;
    }
}

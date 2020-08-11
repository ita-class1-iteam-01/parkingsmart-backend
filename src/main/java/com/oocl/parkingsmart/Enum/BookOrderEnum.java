package com.oocl.parkingsmart.Enum;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
public enum BookOrderEnum {
    FINISHED("FINISHED"),
    ONGOING("ONGOING"),
    BOOKED("BOOKED")
    ;
    String value;
    BookOrderEnum(String value) {
        this.value = value;
    }
}

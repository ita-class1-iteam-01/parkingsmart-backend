package com.oocl.parkingsmart.Enum;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
public enum BookOrderEnum {
    FINISHED("finished"),
    ONGOING("ongoing"),
    BOOKED("booked")
    ;
    String value;
    BookOrderEnum(String value) {
        this.value = value;
    }
}

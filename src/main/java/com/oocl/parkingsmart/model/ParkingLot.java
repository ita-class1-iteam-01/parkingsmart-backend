package com.oocl.parkingsmart.model;

import lombok.Data;

@Data
public class ParkingLot {
    Integer id;
    String name;
    Integer size;
    String address;
    Double longitude;
    Double latitude;
}

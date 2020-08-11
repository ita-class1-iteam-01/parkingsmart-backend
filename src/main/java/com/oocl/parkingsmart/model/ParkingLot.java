package com.oocl.parkingsmart.model;

import lombok.Data;

@Data
public class ParkingLot {
    Integer id;
    String name;
    Integer size;
    Integer price;
    String address;
    Double longitude;
    Double latitude;

    public ParkingLot(Integer id, String name, Integer size, Integer price, String address) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
    }
}

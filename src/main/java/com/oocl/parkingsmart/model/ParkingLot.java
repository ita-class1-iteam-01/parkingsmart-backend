package com.oocl.parkingsmart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer size;
    Double price;
    String address;
    Double longitude;
    Double latitude;

    public ParkingLot(Integer id, String name, Integer size, Double price, String address) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
    }

    public ParkingLot(Integer id, String name, Integer size, Double price, String address, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}

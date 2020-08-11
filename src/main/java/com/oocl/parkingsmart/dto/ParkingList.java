package com.oocl.parkingsmart.dto;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.model.PersonalSpace;
import lombok.Data;

import java.util.List;
@Data
public class ParkingList {
    List<ParkingLot> parkingLots;
    List<PersonalSpace> personalSpaces;
}

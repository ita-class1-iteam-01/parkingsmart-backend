package com.oocl.parkingsmart.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderForm {
    private Integer userId;
    private Integer parkingId;
    private String parkingType;
    private Date reservationStartTime;
    private Date reservationEndTime;
    private String status;
    private String carPort;
    private Double totalPrice;
    private String address;
    private String latitude;
    private String longitude;
}

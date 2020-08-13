package com.oocl.parkingsmart.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderForm {
    private Integer userId;
    private Integer parkingId;
    private String parkingType;
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date reservationStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date reservationEndTime;
    private String status;
    private String carPort;
    private Double totalPrice;
    private String address;
    private String latitude;
    private String longitude;
}

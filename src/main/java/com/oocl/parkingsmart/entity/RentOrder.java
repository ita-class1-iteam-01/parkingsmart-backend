package com.oocl.parkingsmart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String personCarport;
    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rentStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rentEndDate;
    private Double price;
    private String status;
    private String contactPerson;
    private String contactNumber;
    private String address;
    private Double longitude;
    private Double latitude;

}

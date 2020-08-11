package com.oocl.parkingsmart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer personalCarSpaceId;
    private Integer userId;
    private Date rentStartDate;
    private Integer rentalMonths;
    private Double price;
    private String status;
    private String contactPerson;
    private String contactNumber;

}

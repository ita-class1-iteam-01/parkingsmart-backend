package com.oocl.parkingsmart.repository;

import com.oocl.parkingsmart.entity.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder,Integer> {

    @Query("select p from RentOrder p where (\n" +
            "acos(\n" +
            "sin((?2*3.1415)/180) * sin((latitude*3.1415)/180) +\n" +
            "cos((?2*3.1415)/180) * cos((latitude*3.1415)/180) * cos((?1*3.1415)/180 - (longitude*3.1415)/180)\n" +
            ")*6370.996\n" +
            ")<=2")
    List<RentOrder> findAllNearbyPersonalCarPort(Double longitude, Double latitude);
}

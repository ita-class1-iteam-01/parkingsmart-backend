package com.oocl.parkingsmart.repository;

import com.oocl.parkingsmart.entity.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder,Integer> {
    List<RentOrder> findAllNearbyPersonalCarPort(double parseDouble, double parseDouble1);
}

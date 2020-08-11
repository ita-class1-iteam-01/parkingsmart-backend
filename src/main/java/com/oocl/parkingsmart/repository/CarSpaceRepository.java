package com.oocl.parkingsmart.repository;

import com.oocl.parkingsmart.entity.CarSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarSpaceRepository extends JpaRepository<CarSpace,Integer> {
    CarSpace findByParkingLotId(Integer parkingLotId);
    List<CarSpace> findAllByParkingLotId(Integer parkingLotId);
}

package com.oocl.parkingsmart.repository;

import com.oocl.parkingsmart.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder,Integer> {
    @Query("select b from BookOrder b where b.parkingId =?1 and b.status != 'finished' ")
    List<BookOrder> findByStatusNotFINISHED(Integer id);

    BookOrder findByParkingIdAndParkingType(Integer parkingId, String parkingType);
}

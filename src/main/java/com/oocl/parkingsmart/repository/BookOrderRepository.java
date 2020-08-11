package com.oocl.parkingsmart.repository;

import com.oocl.parkingsmart.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder,Integer> {
}

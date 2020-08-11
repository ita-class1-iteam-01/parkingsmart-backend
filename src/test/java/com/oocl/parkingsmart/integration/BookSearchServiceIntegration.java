package com.oocl.parkingsmart.integration;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.service.BookSearchService;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class BookSearchServiceIntegration {

    @Autowired
    private BookSearchService searchService;

    @Test
    void test() throws ParseException {
        PageRequest request = new PageRequest("23.146436", "113.323568", "2020-08-14 18:40:22", "2020-08-15 18:40:22");
        List<ParkingLot> nearbyParkingLot = searchService.findNearbyParkingLot(request);

    }
}

package com.oocl.parkingsmart.integration;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.service.BookSearchService;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookSearchServiceIntegration {

    @Autowired
    private BookSearchService searchService;

    @Test
    void test() throws ParseException {
        PageRequest request = new PageRequest("23.150597", "113.324981", "2020-08-12 16:00:22", "2020-08-31 23:00:22");
        List<ParkingLot> nearbyParkingLot = searchService.findNearbyParkingLot(request);

    }
}

package com.oocl.parkingsmart.integration;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.service.BookSearchPersonalCarPortService;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookSearchPersonalCarPortIntegrationTest {
    @Autowired
    private BookSearchPersonalCarPortService bookSearchPersonalCarPortService;

    @Test
    void should_return_right_size_when_hit_search_given_time_lon_lat() throws ParseException {
        PageRequest request = new PageRequest("22.371738", "113.573064", "2020-08-12 00:00:00", "2020-10-12 00:00:00");
        List<RentOrder> nearbyCarPorts = bookSearchPersonalCarPortService.findNearbyCarPort(request);
        assertEquals(6,nearbyCarPorts.size());
    }
}

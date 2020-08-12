package com.oocl.parkingsmart.integrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ParkingLotControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_return_parkingLot_when_hit_get_given_parkingLot_id_and_time() throws Exception {
        //given
        //when
        mockMvc.perform(get("/parkingLots/"+"4")
                .param("startTime","2017-01-05 12:45:32"). param("endTime","2017-01-05 12:45:32")
        )
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }
}

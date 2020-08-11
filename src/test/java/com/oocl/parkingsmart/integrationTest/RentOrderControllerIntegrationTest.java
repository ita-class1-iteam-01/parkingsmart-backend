package com.oocl.parkingsmart.integrationTest;

import com.oocl.parkingsmart.repository.RentOrderRepository;
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
public class RentOrderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RentOrderRepository repository;

    @Test
    void should_return_rent_orders_when_get_all_given_none() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(get("/rentOrders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());

    }
}

package com.oocl.parkingsmart.integrationTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class RentOrderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RentOrderRepository repository;

    private static Gson gson = new GsonBuilder().create();

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

    @Test
    void should_create_rent_order_when_hit_rent_order_post_given_rent_order() throws Exception {
        //given

        //when
        mockMvc.perform(post("/rentOrders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"personCarport\": \"CW-001\",\n" +
                        "    \"userId\": 1,\n" +
                        "    \"rentStartDate\": \"2020-8-10\",\n" +
                        "    \"rentEndDate\": \"2020-12-10\",\n" +
                        "    \"price\": 300.0,\n" +
                        "    \"status\": \"PUBLISHED\",\n" +
                        "    \"contactPerson\": \"henry\",\n" +
                        "    \"contactNumber\": \"13531915996\",\n" +
                        "    \"address\": \"广东省珠海市唐家湾惠景畅园\",\n" +
                        "    \"longitude\": 0.0,\n" +
                        "    \"latitude\": 0.0\n" +
                        "}"))
        //then
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void should_return_rent_order_when_get_one_given_id() throws Exception {
        //given
        Integer id = 6;
        //when
        //then
        mockMvc.perform(get("/rentOrders/"+id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());

    }
}

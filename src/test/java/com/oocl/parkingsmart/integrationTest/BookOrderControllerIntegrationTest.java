package com.oocl.parkingsmart.integrationTest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookOrderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BookOrderRepository repository;

    @Test
    @Transactional
    void should_add_book_order_when_hit_post_book_order_given_book_order() throws Exception{
        //given
        //when
        mockMvc.perform(post("/bookOrders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":0,\"userId\":1,\"parkingId\":1,\"parkingType\":\"Lots\",\"reservationStartTime\":1597131056871,\"reservationEndTime\":1597131056871,\"status\":\"Finish\"}"))
        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("booking success"));
        List<BookOrder> all = repository.findAll();
        assertTrue(all.size()>0);
    }
}

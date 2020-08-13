package com.oocl.parkingsmart.integrationTest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookOrderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BookOrderRepository repository;

//    @Test
//    @Transactional
//    void should_add_book_order_when_hit_post_book_order_given_book_order() throws Exception{
//        //given
//        //when
//        mockMvc.perform(post("/bookOrders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "    \"userId\": 1,\n" +
//                        "    \"parkingId\": 1,\n" +
//                        "    \"parkingType\": \"lots\",\n" +
//                        "    \"reservationStartTime\": 1597313071359,\n" +
//                        "    \"reservationEndTime\": 1597313071359,\n" +
//                        "    \"status\": \"booked\",\n" +
//                        "    \"carPort\": \"xxx-xx1\",\n" +
//                        "    \"totalPrice\": 100.0,\n" +
//                        "    \"address\": \"str\",\n" +
//                        "    \"latitude\": \"10.1\",\n" +
//                        "    \"longitude\": \"22.1\"\n" +
//                        "}"))
//        //then
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.code").value(0))
//                .andExpect(jsonPath("$.msg").value("booking success"));
//        List<BookOrder> all = repository.findAll();
//        assertTrue(all.size()>0);
//    }

    @Test
    void should_return_book_orders_when_get_all_given_no_parameter() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(get("/bookOrders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());

    }

}

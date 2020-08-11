package com.oocl.parkingsmart.mq.consumer;

import com.oocl.parkingsmart.mq.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.oocl.parkingsmart.mq.message.OrderMessage.QUEUE;

@Component
@Slf4j
public class OrderConusmer {

    @JmsListener(destination = QUEUE)
    public void onMessage(OrderMessage message) {
        //get message, call service method to save order to DB
        log.info("get message:{}", message);
    }
}

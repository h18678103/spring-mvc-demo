package com.hqs.test.service.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Component
public class AmqpProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(MQMsg msg) {
        amqpTemplate.convertAndSend(msg.getExchange(), msg.getRoutingKey(), msg.getMessage());
    }
}

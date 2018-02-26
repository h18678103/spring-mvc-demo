package com.hqs.test.service.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
public class AmqpConsumer implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(AmqpConsumer.class);

    @Override
    public void onMessage(Message message) {
        System.out.println("收到队列消息:<{}>"+message);
        log.info("收到队列消息:<{}>", message);
    }

}

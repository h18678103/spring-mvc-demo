package com.hqs.test.service.impl;

import com.hqs.test.dao.TestDao;
import com.hqs.test.dao.entity.Test;
import com.hqs.test.service.TestService;
import com.hqs.test.service.rabbit.AmqpProducer;
import com.hqs.test.service.rabbit.MQMsg;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Resource
    private AmqpProducer amqpProducer;

    @Override
    public Test getTest(int id) {
        MQMsg mqMsg = new MQMsg();
        mqMsg.setExchange("topic.exchange.one");
        mqMsg.setRoutingKey("pattern.one");
        mqMsg.setMessage(id+"<=id");
        amqpProducer.sendMessage(mqMsg);
        return testDao.getTest(id);
    }
    
}

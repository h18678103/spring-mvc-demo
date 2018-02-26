package com.hqs.test.service.rabbit;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
public class MQMsg {

    private String exchange;
    private String routingKey;
    private String message;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

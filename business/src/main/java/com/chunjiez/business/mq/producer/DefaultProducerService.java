package com.chunjiez.business.mq.producer;

import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Component
public class DefaultProducerService extends AbstractRabbitMqProducerService {

    private static final String DEFAULT_EXCHANGE = "default_exchange";
    private static final String DEFAULT_ROUTING_KEY = "default_routing_key";

    public DefaultProducerService() {
        super(DEFAULT_EXCHANGE, DEFAULT_ROUTING_KEY);
    }

    @Override
    public String business() {
        return "default_message";
    }

    @Override
    public String queue() {
        return "default_queue";
    }

    @Override
    public String getExchangeName() {
        return DEFAULT_EXCHANGE;
    }

    @Override
    public String getRoutingKey() {
        return DEFAULT_ROUTING_KEY;
    }

    @Override
    public void send(Object message) {
        super.send(message);
    }
}

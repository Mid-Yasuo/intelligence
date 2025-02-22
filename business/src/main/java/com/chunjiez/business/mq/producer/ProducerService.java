package com.chunjiez.business.mq.producer;

import com.chunjiez.common.constant.RabbitExchangeEnum;
import com.chunjiez.common.util.CFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
public interface ProducerService {

    String business();

    String queue();

    String getExchangeName();

    default RabbitExchangeEnum getExchangeType() {
        return RabbitExchangeEnum.TOPIC;
    }

    String getRoutingKey();

    boolean autoAck();

    default Map<String, Object> getArguments() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        return args;
    }

    default String getDeadLetterExchange() {
        return "dead";
    }

    ;

    default String getDeadLetterRoutingKey() {
        return "dead";
    }


    void send(Object message);

    void send(Object message, CFunction success, CFunction fail);


    void delaySend(Object message, Integer delay);
}

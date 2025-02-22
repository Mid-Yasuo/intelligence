package com.chunjiez.business.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Slf4j
@Component
public class DefaultConsumerService extends AbstractRabbitMqConsumerService<String> {
    @Override
    public String business() {
        return "default_message";
    }

    @Override
    public boolean autoAck() {
        return true;
    }

    @Override
    public void onConsumer(String data) {
        log.info("consumer msg:{}", data);
    }
}

package com.chunjiez.web.configuration.runner;

import com.chunjiez.business.mq.producer.DefaultProducerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2025/2/22
 */
@Component
public class TestRunner implements ApplicationRunner {

    private final DefaultProducerService defaultProducerService;

    public TestRunner(DefaultProducerService defaultProducerService) {
        this.defaultProducerService = defaultProducerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("TestRunner===>" );
        defaultProducerService.send("test rabbit mq");
    }
}

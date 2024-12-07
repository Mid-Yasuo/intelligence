package com.einstein.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhangChunjie
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.einstein")
public class IntelligenceWebApp {

    public static void main(String[] args) {
        SpringApplication.run(IntelligenceWebApp.class, args);
    }

}

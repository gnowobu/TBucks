package com.tzy.consumer;

import com.tzy.consumer.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.tzy.consumer"})
//@ServletComponentScan(basePackages = {"com.tzy.filter"})
@EnableCaching
public class ConsumerApplication {
    public static void main(String[] args){

        //SpringApplication.run(ConsumerApplication.class, args);
        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class, args);
        SQSMessageService sqsMessageService = app.getBean(SQSMessageService.class);
        sqsMessageService.receiveMessage();
    }
}

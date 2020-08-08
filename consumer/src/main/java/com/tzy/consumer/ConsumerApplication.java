package com.tzy.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.jms.JMSException;

@SpringBootApplication(scanBasePackages = {"com.tzy.consumer"})
public class ConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class, args);


//        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class, args);
//        SQSConnection processService = app.getBean(SQSConnection.class);
//        processService.start();
//        SQSMessageService sqsMessageService = app.getBean(SQSMessageService.class);
//        sqsMessageService.receiveMessage();
    }
}

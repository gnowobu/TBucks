package com.tzy.consumer;

import com.tzy.consumer.service.ProcessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import javax.jms.JMSException;

@SpringBootApplication(scanBasePackages = {"com.tzy.consumer"})
//@ServletComponentScan(basePackages = {"com.tzy.filter"})
@EnableCaching
public class ConsumerApplication {

    public static void main(String[] args) throws JMSException, InterruptedException {

        SpringApplication.run(ConsumerApplication.class, args);


        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class, args);
        ProcessService processService = app.getBean(ProcessService.class);
        processService.processBegin();
//        SQSMessageService sqsMessageService = app.getBean(SQSMessageService.class);
//        sqsMessageService.receiveMessage();
    }
}

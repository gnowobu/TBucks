package com.tzy.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.tzy.ApplicationBootstrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    private AmazonSQS sqsClient;

    @Test
    public void MessasgeSendTest(){

        messageService.sendMessage("test",1);
        Assert.assertTrue(false);
    }


    @Test
    public void getQueueUrlTest(){


        messageService.getQueueUrl("123");
        verify(sqsClient,times(1)).getQueueUrl("123");
    }

}

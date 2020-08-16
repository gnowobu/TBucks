//package com.tzy.service;
//
//
//import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.tzy.consumer.ConsumerApplication;
//import com.tzy.consumer.service.ProcessService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.mockito.Mockito.*;
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ConsumerApplication.class)
//public class ProcessServiceTest {
//
//    @Mock
//    private MessageCreator messageCreator;
//
//    @Autowired
//    private ProcessService processService;
//
//    @Test
//    public void SMSSendTest() throws IOException {
//
//        processService.twilioSMSSend(messageCreator);
//        verify(messageCreator,times(1)).create();
//
//
//    }
//}

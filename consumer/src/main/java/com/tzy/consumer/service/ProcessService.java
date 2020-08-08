package com.tzy.consumer.service;

import com.twilio.rest.api.v2010.account.MessageCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.IOException;

@Component
public class ProcessService {

    private String receiveNumber;

    //@JmsListener(destination = "${jms.queue.name}")
    public void processMessage(String msg) throws IOException{

        //System.out.println(msg);
    }



    @JmsListener(destination = "${jms.queue.name}")
    public void processPhoneNumber(String msg) throws IOException{

        System.out.println(msg);

        String ACCOUNT_SID = "ACb123530858cea485746c7c37e7f1da34";//System.getProperty("ACCOUNT_SID");
        String AUTH_TOKEN = "b764483ccdc2734a2681a6378ea13bf0";//System.getProperty("AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

        receiveNumber = msg;
        String messageBody = "test";

        MessageCreator messageCreator = SMS(receiveNumber,"+12052366643","test");
        twilioSMSSend(messageCreator);

    }

    public MessageCreator SMS(String receiveNumber,String sendNumber,String messageBody) {


        MessageCreator message = Message.creator(
                new com.twilio.type.PhoneNumber(receiveNumber),
                new com.twilio.type.PhoneNumber(sendNumber),
                messageBody);
        return message;
    }

    public void twilioSMSSend(MessageCreator creator){
        creator.create();
    }



}

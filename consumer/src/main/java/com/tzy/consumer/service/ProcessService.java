package com.tzy.consumer.service;



import com.amazon.sqs.javamessaging.SQSConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
@Service
public class ProcessService {

    @Autowired
    private SQSConnection sqsConnection;

    public ProcessService() throws JMSException {
    }

    private class MyListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            try {
                // Cast the received message as TextMessage and print the text to screen.
                System.out.println("Received: " + ((TextMessage) message).getText());
                //message.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


    }

    public void processBegin() throws InterruptedException, JMSException {
        Session session = sqsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(System.getProperty("jms.queue.name"));

        MessageConsumer consumer = session.createConsumer(queue);


// Instantiate and set the message listener for the consumer.
        consumer.setMessageListener(new MyListener());

// Start receiving incoming messages.
        sqsConnection.start();

// Wait for 1 second. The listener onMessage() method is invoked when a message is received.
        Thread.sleep(1000);


    }

}
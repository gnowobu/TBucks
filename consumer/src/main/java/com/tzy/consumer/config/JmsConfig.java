package com.tzy.consumer.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;
import java.util.Stack;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS(){
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        return sqsClient;
    }

    @Bean(name = "connectionFactory")
    public SQSConnectionFactory getConnectionFactory(@Autowired AmazonSQS sqsClient) {

        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                sqsClient
        );
        return connectionFactory;


    }



    @Bean
    public JmsTemplate getJmsTemplate(@Autowired SQSConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return jmsTemplate;
    }

    @Bean(name = "dynamicResolver")
    public DynamicDestinationResolver getTopicDynamicDestinationResolver(){

        return new DynamicDestinationResolver();
    }

    @Bean(name = "jmsListenerContainerFactory")
    @DependsOn({"connectionFactory","dynamicResolver"})
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(@Autowired SQSConnectionFactory connectionFactory, @Autowired DynamicDestinationResolver dynamicDestinationResolver){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setSessionTransacted(false);
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setDestinationResolver(dynamicDestinationResolver);
        jmsListenerContainerFactory.setConcurrency("1");
        jmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return jmsListenerContainerFactory;
    }


//    @Bean
//    public SQSConnection getConnection() throws JMSException {
//        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
//                .withCredentials(new DefaultAWSCredentialsProviderChain()).build();
//
//        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
//                new ProviderConfiguration(),
//                sqsClient
//        );
//      sqsConnection =  connectionFactory.createConnection();;
//      Session session = sqsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//       Queue queue = session.createQueue(System.getProperty("jms.queue.name"));
//
//       MessageConsumer consumer = session.createConsumer(queue);

//// Instantiate and set the message listener for the consumer.
//        consumer.setMessageListener(new MyListener());
    //    return sqsConnection;

//    }


}
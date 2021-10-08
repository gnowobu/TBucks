package com.tzy.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile("unit")
public class AWSTestConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3(){

        AmazonS3 amazonS3 = mock(AmazonS3.class);
        Bucket stubBucket = new Bucket();
        when(amazonS3.createBucket(anyString())).thenReturn(stubBucket);

        return amazonS3;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS(){
        AmazonSQS amazonSQS = mock(AmazonSQS.class);
        //stub getQueueUrl method
        GetQueueUrlResult stubResult = new GetQueueUrlResult();
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(stubResult);

        return amazonSQS;
    }
}

package com.tzy.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
@Profile({"dev","prod"})
public class AWSConfig {

    //private String myAWSAccessKeyId = System.getProperty("AWSkey");
    //private String myAWSSecretKey = System.getProperty("AWSsecretkey");

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3() {

        //BasicAWSCredentials awsCreds = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        return s3Client;
    }



}

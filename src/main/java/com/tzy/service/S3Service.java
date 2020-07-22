package com.tzy.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S3Service {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 amazonS3;

    public S3Service() {

        amazonS3 = getS3ClientWithSuppliedCredentials();
    }

    private String myAWSAccessKeyId = "AKIAWDMVRNFFYOI2DANQ";
    private String myAWSSecretKey = "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";

    private AmazonS3 getS3ClientWithSuppliedCredentials() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        return s3Client;
    }


//    private AmazonS3 getS3ClientUsingDefaultChain() {
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.US_EAST_1)
//                .build();
//        return s3Client;
//    }

    public Bucket createBucket(String bucketName) {
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName)) {
            bucket = amazonS3.createBucket(bucketName);
        } else {
            logger.info("bucket name: {} is not available."
                    + " Try again with a different Bucket name.", bucketName);
        }
        return bucket;
    }


}

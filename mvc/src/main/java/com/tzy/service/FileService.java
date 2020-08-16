package com.tzy.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonS3 amazonS3;


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

    public void uploadFile(File file, String bucketName) throws IOException{

        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType("plain/text");
//        metadata.addUserMetadata("title", "someTitle");
//        request.setMetadata(metadata);
        amazonS3.putObject(request);
    }

    public String uploadFile(String bucketName, MultipartFile file) throws IOException{

        try{
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFileName = uuid + "." + Files.getFileExtension(originalFilename);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName,newFileName,file.getInputStream(),metadata);
            logger.info(String.format("The file name=%s was uploaded to bucket %s", newFileName, bucketName));
            return newFileName;

        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }


    public String getFileUrl(String bucketName, String fileName){

        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(fileName, bucketName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public void deleteBucket(String bucketName){
        amazonS3.deleteBucket(bucketName);
    }
}

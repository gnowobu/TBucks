package com.tzy.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.tzy.ApplicationBootstrap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;

    private String bucketName = "tommytao-s3-bucket-10"; //bucket name must be unique across the server

    private MultipartFile multipartFile;

    @Test
    public void testCreateBucket() {

        Bucket bucket = fileService.createBucket(bucketName);

        Assert.assertNotNull(bucket);
    }

    @Test
    public void testUpload() throws IOException {

        fileService.uploadFile(new File("/Users/mac/test.sql"),"tommytao-s3-bucket1");
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class)); //use amazonS3 here, not s3Service

    }

    @Test
    public void testUploadWithUUID() throws IOException {
        String bucketName="testBucket";
        File file = new File("src/test/input.txt");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));

        fileService.uploadFile(bucketName, multipartFile);
        verify(amazonS3,times(1)).putObject(eq(bucketName),anyString(),any(ByteArrayInputStream.class),any(ObjectMetadata.class));
    }

    @After
    public void tearDown(){

        fileService.deleteBucket(bucketName);
    }

}

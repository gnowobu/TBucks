package service;

import com.amazonaws.services.s3.model.Bucket;
import com.tzy.ApplicationBootstrap;
import com.tzy.service.S3Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class S3ServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private S3Service s3Service;


    @Test
    public void testCreateBucket() {
        String bucketName = "tommytao-s3-bucket-3"; //bucket name must be unique across the server
        Bucket bucket = s3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }

}

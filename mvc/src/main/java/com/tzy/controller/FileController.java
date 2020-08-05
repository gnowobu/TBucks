package com.tzy.controller;

import com.tzy.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    @Autowired
    private FileService fileService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        logger.info("test file name: " + file.getOriginalFilename());
        return fileService.uploadFile(System.getProperty("bucketname"),file);

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getObject(@RequestParam("fileName") String s3Key){

        return fileService.getFileUrl(System.getProperty("bucketname"),s3Key);
    }
}

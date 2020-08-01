package com.tzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.tzy"})
@ServletComponentScan(basePackages = {"com.tzy.filter"})
@EnableCaching
public class ApplicationBootstrap {
    public static void main(String[] args){
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}

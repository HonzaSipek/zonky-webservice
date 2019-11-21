package com.zonky.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRunner.class);

    public static void main(String[] args) {
        LOG.info("Zonky webservice is starting...");
        SpringApplication.run(ServiceRunner.class, args);
    }
}

package com.sxkl.project.cloudwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudWorkApplication.class, args);
    }
}

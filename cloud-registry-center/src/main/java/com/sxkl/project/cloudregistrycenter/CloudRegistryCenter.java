package com.sxkl.project.cloudregistrycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudRegistryCenter {

    public static void main(String[] args) {
        SpringApplication.run(CloudRegistryCenter.class, args);
    }
}

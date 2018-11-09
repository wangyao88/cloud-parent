package com.sxkl.project.cloudnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNoteApplication.class, args);
	}
}

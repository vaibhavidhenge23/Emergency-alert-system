package com.resource_tracker_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResourceTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceTrackerServiceApplication.class, args);
	}

}

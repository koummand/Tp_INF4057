package com.m1.fonda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceCompteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCompteApplication.class, args);
	}

}

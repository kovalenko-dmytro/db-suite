package com.gmail.apachdima.dbsuite.compareservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CompareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompareServiceApplication.class, args);
	}

}

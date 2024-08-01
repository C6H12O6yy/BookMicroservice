package com.example.BookManagementServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementServerApplication.class, args);
	}

}

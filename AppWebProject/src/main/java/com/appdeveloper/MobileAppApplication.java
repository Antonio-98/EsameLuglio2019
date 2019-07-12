package com.appdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.app.controller", "com.app.service" })
@SpringBootApplication
public class MobileAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppApplication.class, args);
	}
}
package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling  // batch 어노테이션
@SpringBootApplication
public class OfTodayApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OfTodayApplication.class, args);
	}

}

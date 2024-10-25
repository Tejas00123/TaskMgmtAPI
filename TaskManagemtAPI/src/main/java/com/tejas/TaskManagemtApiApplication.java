package com.tejas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskManagemtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagemtApiApplication.class, args);
	}
	
	


}

package com.nayan.search.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.in28minutes.springboot.service"})
//@ComponentScan(basePackages= {"com.in28minutes.springboot.service"})
public class StudentServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServicesApplication.class, args);
	}
}

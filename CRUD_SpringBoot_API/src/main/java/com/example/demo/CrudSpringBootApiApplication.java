package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
@EnableAutoConfiguration //configure tự động dựa trên jar dependency đã thêm
public class CrudSpringBootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApiApplication.class, args);
	}

	 
}

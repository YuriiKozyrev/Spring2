package com.myshop.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
//		System.out.println(encoder.encode("pass"));
	}

}

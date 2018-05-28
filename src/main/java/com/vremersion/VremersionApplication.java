package com.vremersion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vremersion.Dao")
public class VremersionApplication {

	public static void main(String[] args) {
		System.out.println( "Hello World!" );
		SpringApplication.run(VremersionApplication.class, args);
	}
}

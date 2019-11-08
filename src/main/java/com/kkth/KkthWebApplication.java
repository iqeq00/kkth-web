package com.kkth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kkth.web.mapper")
public class KkthWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(KkthWebApplication.class, args);
	}

}
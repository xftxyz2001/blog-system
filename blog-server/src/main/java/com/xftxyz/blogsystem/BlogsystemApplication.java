package com.xftxyz.blogsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @MapperScan("com.xftxyz.blogsystem.mapper")
@SpringBootApplication
public class BlogsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsystemApplication.class, args);
	}

}

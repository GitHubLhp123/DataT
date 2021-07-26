package com.datat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mlhp1
 */
@MapperScan(value = "com.datat.*.mapper")
@SpringBootApplication
public class DataTApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTApplication.class, args);
	}
}

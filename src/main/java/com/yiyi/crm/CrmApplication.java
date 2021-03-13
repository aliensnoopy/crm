package com.yiyi.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yiyi.crm.dao")
public class CrmApplication {

	public static void main(String[] args) {
    SpringApplication.run(CrmApplication.class, args);
	}

}

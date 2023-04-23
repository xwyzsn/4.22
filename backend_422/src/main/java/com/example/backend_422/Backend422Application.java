package com.example.backend_422;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.backend_422.mapper")
public class Backend422Application {

    public static void main(String[] args) {
        SpringApplication.run(Backend422Application.class, args);
    }

}

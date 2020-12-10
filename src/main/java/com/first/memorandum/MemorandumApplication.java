package com.first.memorandum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.first.memorandum.*"})
@MapperScan(basePackages = {"com.first.memorandum.mapper"})
public class MemorandumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemorandumApplication.class, args);
    }

}

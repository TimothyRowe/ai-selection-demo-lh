package com.selection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.selection.repository")
@EnableScheduling
public class SmartSelectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartSelectionApplication.class, args);
    }
}

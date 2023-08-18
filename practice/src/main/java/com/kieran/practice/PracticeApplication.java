package com.kieran.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Practice Module
 */
@MapperScan(basePackages = "com.kieran.practice.mapper")
@SpringBootApplication
public class PracticeApplication {

    /**
     * application starter
     * @param args args...
     */
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

}

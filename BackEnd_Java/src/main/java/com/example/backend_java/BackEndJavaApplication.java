package com.example.backend_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
public class BackEndJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndJavaApplication.class, args);
    }

}

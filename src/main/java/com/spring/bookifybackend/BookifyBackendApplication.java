package com.spring.bookifybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BookifyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookifyBackendApplication.class, args);
    }

}

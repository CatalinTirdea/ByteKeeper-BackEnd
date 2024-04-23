package com.bytekeeper.backend;

import com.bytekeeper.backend.controller.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.bytekeeper.backend.model")
//@ComponentScan(basePackages = "com.bytekeeper.backend")
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}

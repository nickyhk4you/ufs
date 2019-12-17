package com.ufs.campaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UfsApplication.class, args);
    }

}

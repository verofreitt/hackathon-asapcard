package com.asap.hackathonasap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.asap.hackathonasap")
public class HackathonasapApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackathonasapApplication.class, args);
    }
}
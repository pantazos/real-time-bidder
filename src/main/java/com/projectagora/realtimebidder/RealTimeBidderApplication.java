package com.projectagora.realtimebidder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealTimeBidderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealTimeBidderApplication.class, args);
    }
}

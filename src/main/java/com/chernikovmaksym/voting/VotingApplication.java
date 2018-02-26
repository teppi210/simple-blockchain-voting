package com.chernikovmaksym.voting;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VotingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(VotingApplication.class)
                .properties("spring.config.name=config")
                .build()
                .run(args);
    }
}

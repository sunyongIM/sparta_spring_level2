package com.example.level2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Level2Application {

    public static void main(String[] args) {
        SpringApplication.run(Level2Application.class, args);
    }

}

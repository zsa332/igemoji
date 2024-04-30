package com.ssafy.igemoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class IgemojiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgemojiApplication.class, args);
    }

}

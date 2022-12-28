package com.individual.individual_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IndividualProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualProjectApplication.class, args);
    }

}

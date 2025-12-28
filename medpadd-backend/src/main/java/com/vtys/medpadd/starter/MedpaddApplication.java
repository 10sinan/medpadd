package com.vtys.medpadd.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.vtys"})
@ComponentScan(basePackages = {"com.vtys"})
@EnableJpaRepositories(basePackages = {"com.vtys"})
public class MedpaddApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedpaddApplication.class, args);
    }
}

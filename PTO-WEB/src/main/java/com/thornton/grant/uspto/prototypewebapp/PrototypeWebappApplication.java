package com.thornton.grant.uspto.prototypewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.thornton.grant.uspto")
@EntityScan(basePackages = "com.thornton.grant.uspto")
@ComponentScan(basePackages = "com.thornton.grant.uspto")
public class PrototypeWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeWebappApplication.class, args);
    }
}

package com.vkopendoh.orgapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vkopendoh.orgapp"})
public class OrgappApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrgappApplication.class, args);
    }
}

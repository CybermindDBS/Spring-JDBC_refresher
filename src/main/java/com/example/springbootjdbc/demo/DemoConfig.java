package com.example.springbootjdbc.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("profile1")
public class DemoConfig {
    @Bean(name = "profileBasedValue")
    Integer getInt() {
        return 1;
    }
}

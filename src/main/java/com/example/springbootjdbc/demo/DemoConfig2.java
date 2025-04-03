package com.example.springbootjdbc.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("profile2")
public class DemoConfig2 {
    @Bean(name = "profileBasedValue")
    Integer getInt() {
        return 2;
    }
}

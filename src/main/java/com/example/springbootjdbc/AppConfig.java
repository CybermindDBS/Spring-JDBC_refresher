package com.example.springbootjdbc;

import com.example.springbootjdbc.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Logger getLogger() {
        return LoggerUtil.getLogger();
    }
}

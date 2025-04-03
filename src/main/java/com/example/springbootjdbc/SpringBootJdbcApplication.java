package com.example.springbootjdbc;

import com.example.springbootjdbc.service.EmployeeService;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class SpringBootJdbcApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootJdbcApplication.class, args);
        EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
        Logger logger = applicationContext.getBean(Logger.class);

        logger.info("Options:-");
        logger.debug("View outputs for Spring Boot, option: 1");
        logger.debug("View outputs for Spring Boot JDBC - part 1, option: 2");
        logger.info("Enter your option: ");

        char option;
        Scanner scanner = new Scanner(System.in);
        option = scanner.next().charAt(0);

        switch (option) {
            case '1' -> {
                //Demo: @Value and @ConfigurationProperties
                employeeService.demo1();
                //Demo: profiles in spring boot (change profile value in application.properties)
                employeeService.demo2();
            }
            case '2' -> {
                //Demo: JDBCTemplate CRUD operations
                employeeService.demo3();
                //Demo: BeanPropertyRowMapper, ResultSetExtractor, RowMapperResultSetExtractor, RowCallbackHandler
                employeeService.demo4();
            }
        }
    }
}

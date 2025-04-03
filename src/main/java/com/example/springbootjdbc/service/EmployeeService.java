package com.example.springbootjdbc.service;

import com.example.springbootjdbc.dao.EmployeeDAO;
import com.example.springbootjdbc.demo.SomeClass;
import com.example.springbootjdbc.dto.EmployeeDTO;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import static com.example.springbootjdbc.utils.LoggerUtil.newLine;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    //Injects Value from application.properties file
    @Value("${example.some-value}")
    Integer iVal;

    @Autowired
    SomeClass someClass;

    @Autowired()
    Logger logger;

    @Value("${spring.profiles.active}")
    String profileName;

    @Autowired
    Integer profileBasedValue;

    public void demo1() {
        logger.info("Demo for: @Value");
        logger.debug("The value set by @Value is " + iVal + newLine);
        logger.info("Demo for: @ConfigurationProperties");
        logger.debug(someClass + newLine);
    }

    public void demo2() {
        logger.info("Demo for: Profiles in Spring Boot");
        logger.debug("The current profile is: " + profileName);
        logger.debug("Autowired value based on profile is: " + profileBasedValue);
    }

    public void demo3() {
        logger.info("Table: Employee" + newLine);
        logger.info("Example for queryForObject & queryForList:");
        logger.debug("Total salary of all employees: " + employeeDAO.getTotalSalaryExpense());
        logger.debug("List of all employee names: " + employeeDAO.getAllEmployeeName() + newLine);

        logger.info("Example for CRUD operations with JdbcTemplate:");
        logger.debug("Create: Creating new employee record ('AnyName', 'IT', 90000)");
        employeeDAO.create(new EmployeeDTO(null, "AnyName", "IT", 90000));
        logger.debug("ReadAll (using RowMapper interface): " + employeeDAO.readAll());
        logger.debug("Update: Updating 1st employee record");

        //Example for @Transactional, set salary to '900' to activate.
        try {
            employeeDAO.update(new EmployeeDTO(1, "Jane Doe", "GFX", 99999));
        } catch (SQLException e) {
            logger.debug("Error thrown when trying to Update, Reverting changes...");
        }
        logger.debug("Delete: Deleting 3rd employee record");
        employeeDAO.delete(3);
        logger.info("Employee table now: " + employeeDAO.readAll() + newLine);
    }

    public void demo4()
    {
        logger.info("Example for different ways to read:");
        logger.debug("BeanPropertyRowMapper: " + employeeDAO.readAll2());
        logger.debug("ResultSetExtractor: " + employeeDAO.readAll3());
        logger.debug("RowMapperResultSetExtractor: " + employeeDAO.readAll4());
        logger.debug("RowCallbackHandler: " + employeeDAO.readAll5());
    }
}

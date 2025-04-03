package com.example.springbootjdbc.dao;

import com.example.springbootjdbc.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO {

    //Spring Boot will inject automatically created JdbcTemplate bean.
    @Autowired
    JdbcTemplate jdbcTemplate;


    //Demo: QueryForObject & QueryForList
    public Integer getTotalSalaryExpense() {
        return jdbcTemplate.queryForObject("select sum(salary) from employee", Integer.class);
    }

    public List<String> getAllEmployeeName() {
        return jdbcTemplate.queryForList("select name from employee", String.class);
    }


    //Demo: CRUD operations with JdbcTemplate
    public void create(EmployeeDTO employeeDTO) {
        jdbcTemplate.update("insert into employee (name, department, salary) values (?, ?, ?)", employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary());
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(EmployeeDTO employeeDTO) throws SQLException {
            jdbcTemplate.update("update employee set name = ?, department = ?, salary = ? where id = ?", employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary(), employeeDTO.getId());

            // @Transactional will not update and rollback if salary is set to 900.
            if (employeeDTO.getSalary() == 900)
                throw new SQLException();
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from employee where id = ?", id);
    }

    //Demo: RowMapper interface
    public List<EmployeeDTO> readAll() {
        return jdbcTemplate.query("select * from employee", new RowMapper<EmployeeDTO>() {
            public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(rs.getInt("id"));
                employeeDTO.setName(rs.getString("name"));
                employeeDTO.setDepartment(rs.getString("department"));
                employeeDTO.setSalary(rs.getInt("salary"));
                return employeeDTO;
            }
        });
    }

    //Demo: BeanPropertyRowMapper class
    public List<EmployeeDTO> readAll2() {
        return jdbcTemplate.query("select * from employee", new BeanPropertyRowMapper<>(EmployeeDTO.class));
    }

    //Demo: ResultSetExtractor interface
    public List<EmployeeDTO> readAll3() {
        return jdbcTemplate.query("select * from employee", new ResultSetExtractor<List<EmployeeDTO>>() {
            public List<EmployeeDTO> extractData(ResultSet rs) throws SQLException {

                List<EmployeeDTO> employeeDTOList = new ArrayList<>();

                while (rs.next()) {
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setId(rs.getInt("id"));
                    employeeDTO.setName(rs.getString("name"));
                    employeeDTO.setDepartment(rs.getString("department"));
                    employeeDTO.setSalary(rs.getInt("salary"));
                    employeeDTOList.add(employeeDTO);
                }
                return employeeDTOList;
            }
        });
    }

    //Demo: RowMapperResultSetExtractor class
    public List<EmployeeDTO> readAll4() {
        return jdbcTemplate.query("select * from employee", new ResultSetExtractor<List<EmployeeDTO>>() {
            @Override
            public List<EmployeeDTO> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                RowMapper<EmployeeDTO> rowMapper = new BeanPropertyRowMapper<>(EmployeeDTO.class);
                return new RowMapperResultSetExtractor<>(rowMapper).extractData(resultSet);
            }
        });
    }

    //Demo: RowCallbackHandler interface
    public List<EmployeeDTO> readAll5() {

        //We will add the records to this list.
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        jdbcTemplate.query("select * from employee", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(rs.getInt("id"));
                employeeDTO.setName(rs.getString("name"));
                employeeDTO.setDepartment(rs.getString("department"));
                employeeDTO.setSalary(rs.getInt("salary"));
                employeeDTOList.add(employeeDTO);
            }
        });
        return employeeDTOList;
    }
}

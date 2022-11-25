package com.bodnar.dao.impl;

import com.bodnar.dao.EmployeeDao;
import com.bodnar.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDaoImpl implements EmployeeDao {
    private static final String FIND_ALL = "SELECT * FROM employee";
    private static final String CREATE = "INSERT employee(name, surname," +
            "gender, birthday, teritory_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employee SET name=?,  surname=?, gender=?," +
            "birthday=?, teritory_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM employee WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM employee WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        Optional<Employee> employee;
        try {
            employee = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Employee.class), employeeId));
        } catch (EmptyResultDataAccessException e) {
            employee = Optional.empty();
        }
        return employee;
    }

    @Override
    public int create(Employee employee) {
        return jdbcTemplate.update(CREATE, employee.getName(), employee.getSurname(),
                employee.getGender(), employee.getBirthday(), employee.getTeritoryId());
    }

    @Override
    public int update(Integer employeeId, Employee employee) {
        return jdbcTemplate.update(UPDATE, employee.getName(), employee.getSurname(),
                employee.getGender(), employee.getBirthday(), employee.getTeritoryId(), employeeId);
    }

    @Override
    public int delete(Integer employeeId) {
        return jdbcTemplate.update(DELETE, employeeId);
    }
}

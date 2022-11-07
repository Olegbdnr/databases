package com.bodnar.service.impl;

import com.bodnar.dao.EmployeeDao;
import com.bodnar.domain.Employee;
import com.bodnar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        return employeeDao.findById(employeeId);
    }

    @Override
    public int create(Employee employee) {
        return employeeDao.create(employee);
    }

    @Override
    public int update(Integer employeeId, Employee employee) {
        return employeeDao.update(employeeId, employee);
    }

    @Override
    public int delete(Integer employeeId) {
        return employeeDao.delete(employeeId);
    }
}

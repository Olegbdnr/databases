package com.bodnar.controller.impl;

import com.bodnar.controller.EmployeeController;
import com.bodnar.domain.Employee;
import com.bodnar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeControllerImpl implements EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Override
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        return employeeService.findById(employeeId);
    }

    @Override
    public int create(Employee employee) {
        return employeeService.create(employee);
    }

    @Override
    public int update(Integer employeeId, Employee employee) {
        return employeeService.update(employeeId, employee);
    }

    @Override
    public int delete(Integer employeeId) {
        return employeeService.delete(employeeId);
    }
}

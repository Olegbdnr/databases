package bodnar.service.impl;

import bodnar.domain.Employee;
import bodnar.domain.Teritory;
import bodnar.exception.EmployeeNotExistException;
import bodnar.exception.TeritoryNotFoundException;
import bodnar.repository.TeritoryRepository;
import bodnar.service.EmployeeService;
import bodnar.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeritoryRepository teritoryRepository;

    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotExistException(id));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee create(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Employee create(Employee employee, Integer teritoryId) {
        Teritory teritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoryNotFoundException(teritoryId));
        employee.setTeritory(teritory);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public void update(Integer id, Employee employee) {
        Employee newEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotExistException(id));
        newEmployee.setName(employee.getName());
        newEmployee.setSurname(employee.getSurname());
        newEmployee.setBirthday(employee.getBirthday());
        newEmployee.setGender(employee.getGender());
        employeeRepository.save(newEmployee);
    }

    @Transactional
    public void update(Integer id, Employee employee, Integer teritoryId) {
        Employee newEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotExistException(id));
        Teritory newTeritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoryNotFoundException(teritoryId));
        newEmployee.setName(employee.getName());
        newEmployee.setSurname(employee.getSurname());
        newEmployee.setBirthday(employee.getBirthday());
        newEmployee.setGender(employee.getGender());
        newEmployee.setTeritory(newTeritory);
        employeeRepository.save(newEmployee);
    }

    @Transactional
    public void delete(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotExistException(id));
        employeeRepository.delete(employee);
    }
}

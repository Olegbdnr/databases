package bodnar.service;

import bodnar.domain.Employee;

public interface EmployeeService extends GeneralService<Employee, Integer>{
    Employee create(Employee employee, Integer teritoryId);

    void update(Integer id, Employee employee, Integer teritoryId);
}

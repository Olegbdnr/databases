package bodnar.controller;

import bodnar.dto.EmployeeDto;
import bodnar.domain.Employee;
import bodnar.dto.assembler.EmployeeDtoAssembler;
import bodnar.service.EmployeeService;
import bodnar.service.TeritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TeritoryService teritoryService;

    @Autowired
    EmployeeDtoAssembler employeeDtoAssembler;

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer employeeId) {
        Employee employee = employeeService.findById(employeeId);
        EmployeeDto employeeDto = employeeDtoAssembler.toModel(employee);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        CollectionModel<EmployeeDto> employeeDtos = employeeDtoAssembler.toCollectionModel(employees);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{teritoryId}")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody Employee employee, @PathVariable Integer teritoryId) {
        Employee newEmployee = employeeService.create(employee, teritoryId);
        EmployeeDto employeeDto = employeeDtoAssembler.toModel(newEmployee);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}/{teritoryId}")
    public ResponseEntity<?> updateEmployee (@PathVariable Integer employeeId,
                                             @RequestBody Employee employee,
                                             @PathVariable Integer teritoryId) {
        employeeService.update(employeeId, employee, teritoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

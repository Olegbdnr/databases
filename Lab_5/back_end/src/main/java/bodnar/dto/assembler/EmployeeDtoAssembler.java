package bodnar.dto.assembler;

import bodnar.controller.EmployeeController;
import bodnar.dto.EmployeeDto;
import bodnar.controller.TeritoryController;
import bodnar.domain.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeDtoAssembler implements RepresentationModelAssembler<Employee, EmployeeDto> {

    @Override
    public EmployeeDto toModel(Employee employee) {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .gender(employee.getGender())
                .birthday(employee.getBirthday())
                .build();
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).getEmployee(employeeDto.getId())).withSelfRel();
        Link teritoryLink = linkTo(methodOn(TeritoryController.class)
                .getTeritory(employee.getTeritory().getId())).withRel("teritory");
        employeeDto.add(selfLink);
        employeeDto.add(teritoryLink);
        return employeeDto;
    }

    @Override
    public CollectionModel<EmployeeDto> toCollectionModel(Iterable<? extends Employee> employees) {
        CollectionModel<EmployeeDto> employeeDtos = RepresentationModelAssembler.super.toCollectionModel(employees);
        Link selfLink = linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel();
        employeeDtos.add(selfLink);
        return employeeDtos;
    }
}

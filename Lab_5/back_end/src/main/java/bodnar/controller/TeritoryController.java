package bodnar.controller;

import bodnar.domain.Employee;
import bodnar.domain.PatrolBot;
import bodnar.domain.Route;
import bodnar.domain.Teritory;
import bodnar.dto.EmployeeDto;
import bodnar.dto.PatrolBotDto;
import bodnar.dto.RouteDto;
import bodnar.dto.TeritoryDto;
import bodnar.dto.assembler.EmployeeDtoAssembler;
import bodnar.dto.assembler.PatrolBotDtoAssembler;
import bodnar.dto.assembler.RouteDtoAssembler;
import bodnar.dto.assembler.TeritoryDtoAssembler;
import bodnar.service.TeritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/teritories")
public class TeritoryController {
    @Autowired
    TeritoryService teritoryService;

    @Autowired
    TeritoryDtoAssembler teritoryDtoAssembler;

    @Autowired
    EmployeeDtoAssembler employeeDtoAssembler;

    @Autowired
    PatrolBotDtoAssembler patrolBotDtoAssembler;

    @Autowired
    RouteDtoAssembler routeDtoAssembler;

    @GetMapping(path = "/{teritoryId}")
    public ResponseEntity<TeritoryDto> getTeritory(@PathVariable Integer teritoryId) {
        Teritory teritory = teritoryService.findById(teritoryId);
        TeritoryDto teritoryDto = teritoryDtoAssembler.toModel(teritory);
        return new ResponseEntity<>(teritoryDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<TeritoryDto>> getAllTeritories() {
        List<Teritory> teritories = teritoryService.findAll();
        CollectionModel<TeritoryDto> teritoryDtos = teritoryDtoAssembler.toCollectionModel(teritories);
        return new ResponseEntity<>(teritoryDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{teritoryId}/patrolBots")
    public ResponseEntity<CollectionModel<PatrolBotDto>> getPatrolBotsForTeritory(@PathVariable Integer teritoryId) {
        Teritory teritory = teritoryService.findById(teritoryId);
        List<PatrolBot> patrolBotsForTeritory = teritory.getPatrolBots();
        CollectionModel<PatrolBotDto> patrolBotDtos = patrolBotDtoAssembler.toCollectionModel(patrolBotsForTeritory);
        return new ResponseEntity<>(patrolBotDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{teritoryId}/employees")
    public ResponseEntity<CollectionModel<EmployeeDto>> getEmployeesForTeritory(@PathVariable Integer teritoryId) {
        Teritory teritory = teritoryService.findById(teritoryId);
        List<Employee> employeesForTeritory = teritory.getEmployees();
        CollectionModel<EmployeeDto> employeeDtos = employeeDtoAssembler.toCollectionModel(employeesForTeritory);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{teritoryId}/routes")
    public ResponseEntity<CollectionModel<RouteDto>> getRoutesForTeritory(@PathVariable Integer teritoryId) {
        Teritory teritory = teritoryService.findById(teritoryId);
        List<Route> routesForTeritory = teritory.getRoutes();
        CollectionModel<RouteDto> routeDtos = routeDtoAssembler.toCollectionModel(routesForTeritory);
        return new ResponseEntity<>(routeDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{locationId}")
    public ResponseEntity<TeritoryDto> addTeritory(@RequestBody Teritory teritory, @PathVariable Integer locationId) {
        Teritory newTeritory = teritoryService.create(teritory, locationId);
        TeritoryDto teritoryDto = teritoryDtoAssembler.toModel(newTeritory);
        return new ResponseEntity<>(teritoryDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{teritoryId}/{locationId}")
    public ResponseEntity<?> updateTeritory(@PathVariable Integer teritoryId,
                                            @RequestBody Teritory teritory,
                                            @PathVariable Integer locationId) {
        teritoryService.update(teritoryId, teritory, locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{teritoryId}")
    public ResponseEntity<?> deleteTeritory(@PathVariable Integer teritoryId) {
        teritoryService.delete(teritoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package bodnar.controller;

import bodnar.domain.Route;
import bodnar.dto.RouteDto;
import bodnar.dto.assembler.RouteDtoAssembler;
import bodnar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    RouteDtoAssembler routeDtoAssembler;

    @GetMapping(path = "/{routeId}")
    public ResponseEntity<RouteDto> getRoute(@PathVariable Integer routeId) {
        Route route = routeService.findById(routeId);
        RouteDto routeDto = routeDtoAssembler.toModel(route);
        return new ResponseEntity<>(routeDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<RouteDto>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        CollectionModel<RouteDto> routeDtos = routeDtoAssembler.toCollectionModel(routes);
        return new ResponseEntity<>(routeDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{teritoryaId}")
    public ResponseEntity<RouteDto> addRoute(@RequestBody Route route, @PathVariable Integer teritoryaId) {
        Route newRoute = routeService.create(route, teritoryaId);
        RouteDto routeDto = routeDtoAssembler.toModel(newRoute);
        return new ResponseEntity<>(routeDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{routeId}/{teritoryaId}")
    public ResponseEntity<?> updateRoute(@PathVariable Integer routeId,
                                         @RequestBody Route route,
                                         @PathVariable Integer teritoryaId) {
        routeService.update(routeId, route, teritoryaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

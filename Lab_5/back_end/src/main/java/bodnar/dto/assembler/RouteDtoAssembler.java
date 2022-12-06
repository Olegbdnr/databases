package bodnar.dto.assembler;

import bodnar.domain.Route;
import bodnar.dto.RouteDto;
import bodnar.controller.RouteController;
import bodnar.controller.TeritoryController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class RouteDtoAssembler implements RepresentationModelAssembler<Route, RouteDto> {

    @Override
    public RouteDto toModel(Route route) {
        RouteDto routeDto = RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .length(route.getLength())
                .build();
        Link selfLink = linkTo(methodOn(RouteController.class).getRoute(routeDto.getId())).withSelfRel();
        Link teritoryLink = linkTo(methodOn(TeritoryController.class)
                .getTeritory(route.getTeritory().getId())).withRel("teritory");
        routeDto.add(selfLink);
        routeDto.add(teritoryLink);
        return routeDto;
    }

    @Override
    public CollectionModel<RouteDto> toCollectionModel(Iterable<? extends Route> routes) {
        CollectionModel<RouteDto> routeDtos = RepresentationModelAssembler.super.toCollectionModel(routes);
        Link selfLink = linkTo(methodOn(RouteController.class).getAllRoutes()).withSelfRel();
        routeDtos.add(selfLink);
        return routeDtos;
    }
}

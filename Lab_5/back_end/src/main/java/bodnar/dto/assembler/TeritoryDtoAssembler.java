package bodnar.dto.assembler;

import bodnar.controller.LocationController;
import bodnar.domain.Teritory;
import bodnar.dto.TeritoryDto;
import bodnar.controller.TeritoryController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeritoryDtoAssembler implements RepresentationModelAssembler<Teritory, TeritoryDto> {

    @Override
    public TeritoryDto toModel(Teritory teritory) {
        TeritoryDto teritoryDto = TeritoryDto.builder()
                .id(teritory.getId())
                .phone(teritory.getPhone())
                .build();
        Link selfLink = linkTo(methodOn(TeritoryController.class).getTeritory(teritoryDto.getId())).withSelfRel();
        Link locationLink = WebMvcLinkBuilder.linkTo(methodOn(LocationController.class)
                .getLocation(teritory.getLocation().getId())).withRel("location");
        Link patrolBotSLink = linkTo(methodOn(TeritoryController.class)
                .getPatrolBotsForTeritory(teritoryDto.getId())).withRel("patrolBots");
        Link routesLink = linkTo(methodOn(TeritoryController.class)
                .getRoutesForTeritory(teritoryDto.getId())).withRel("routes");
        Link employeesLink = linkTo(methodOn(TeritoryController.class)
                .getEmployeesForTeritory(teritory.getId())).withRel("employees");
        teritoryDto.add(selfLink);
        teritoryDto.add(locationLink);
        teritoryDto.add(patrolBotSLink);
        teritoryDto.add(routesLink);
        teritoryDto.add(employeesLink);
        return teritoryDto;
    }

    @Override
    public CollectionModel<TeritoryDto> toCollectionModel(Iterable<? extends Teritory> teritories) {
        CollectionModel<TeritoryDto> teritoryDtos = RepresentationModelAssembler.super.toCollectionModel(teritories);
        Link selfLink = linkTo(methodOn(TeritoryController.class).getAllTeritories()).withSelfRel();
        teritoryDtos.add(selfLink);
        return teritoryDtos;
    }
}

package bodnar.dto.assembler;

import bodnar.controller.LocationController;
import bodnar.domain.Location;
import bodnar.dto.LocationDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LocationDtoAssembler implements RepresentationModelAssembler<Location, LocationDto> {

    @Override
    public LocationDto toModel(Location location) {
        LocationDto locationDto = LocationDto.builder()
                .id(location.getId())
                .country(location.getCountry())
                .city(location.getCity())
                .street(location.getStreet())
                .buildingNumber(location.getBuildingNumber())
                .build();
        Link selfLink = linkTo(methodOn(LocationController.class).getLocation(locationDto.getId())).withSelfRel();
        Link teritoriesLink = linkTo(methodOn(LocationController.class)
                .getTeritoriesForLocation(locationDto.getId())).withRel("teritories");
                locationDto.add(teritoriesLink);
        locationDto.add(selfLink);
        locationDto.add(teritoriesLink);
        return locationDto;
    }

    @Override
    public CollectionModel<LocationDto> toCollectionModel (Iterable<? extends Location> locations) {
        CollectionModel<LocationDto> locationDtos = RepresentationModelAssembler.super.toCollectionModel(locations);
        Link selfLink = linkTo(methodOn(LocationController.class).getAllLocations()).withSelfRel();
        locationDtos.add(selfLink);
        return locationDtos;
    }
}

package bodnar.dto.assembler;

import bodnar.dto.ManufacturerDto;
import bodnar.controller.ManufacturerController;
import bodnar.domain.Manufacturer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ManufacturerDtoAssembler implements RepresentationModelAssembler<Manufacturer, ManufacturerDto> {

    @Override
    public ManufacturerDto toModel(Manufacturer manufacturer) {
        ManufacturerDto manufacturerDto = ManufacturerDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .build();
        Link selfLink = linkTo(methodOn(ManufacturerController.class).getManufacturer(manufacturerDto.getId())).withSelfRel();
        Link completeSetsLink = linkTo(methodOn(ManufacturerController.class)
                .getCompleteSetsForManufacturer(manufacturerDto.getId())).withRel("completeSets");
        manufacturerDto.add(selfLink);
        manufacturerDto.add(completeSetsLink);
        return manufacturerDto;
    }

    @Override
    public CollectionModel<ManufacturerDto> toCollectionModel(Iterable<? extends Manufacturer> manufacturers) {
        CollectionModel<ManufacturerDto> manufacturerDtos = RepresentationModelAssembler.super.toCollectionModel(manufacturers);
        Link selfLink = linkTo(methodOn(ManufacturerController.class).getAllManufacturers()).withSelfRel();
        manufacturerDtos.add(selfLink);
        return manufacturerDtos;
    }
}

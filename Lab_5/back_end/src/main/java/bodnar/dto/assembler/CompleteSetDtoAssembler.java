package bodnar.dto.assembler;

import bodnar.controller.ManufacturerController;
import bodnar.domain.CompleteSet;
import bodnar.dto.CompleteSetDto;
import bodnar.controller.CompleteSetController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CompleteSetDtoAssembler implements RepresentationModelAssembler<CompleteSet, CompleteSetDto> {

    @Override
    public CompleteSetDto toModel(CompleteSet completeSet) {
        CompleteSetDto completeSetDto = CompleteSetDto.builder()
                .id(completeSet.getId())
                .name(completeSet.getName())
                .averageSpeed(completeSet.getAverageSpeed())
                .batteryReserve(completeSet.getBatteryReserve())
                .length(completeSet.getLength())
                .width(completeSet.getWidth())
                .height(completeSet.getHeight())
                .build();
        Link selfLink = linkTo(methodOn(CompleteSetController.class).getCompleteSet(completeSetDto.getId())).withSelfRel();
        Link manufacturerLink = WebMvcLinkBuilder.linkTo(methodOn(ManufacturerController.class)
                .getManufacturer(completeSet.getManufacturer().getId())).withRel("manufacturer");
        Link patrolBotsLink = linkTo(methodOn(CompleteSetController.class)
                .getPatrolBotsForCompleteSet(completeSet.getId())).withRel("patrolBots");
        Link camerasLink = linkTo(methodOn(CompleteSetController.class)
                .getCamerasForCompleteSet(completeSet.getId())).withRel("cameras");
        completeSetDto.add(selfLink);
        completeSetDto.add(manufacturerLink);
        completeSetDto.add(patrolBotsLink);
        completeSetDto.add(camerasLink);
        return completeSetDto;
    }

    @Override
    public CollectionModel<CompleteSetDto> toCollectionModel(Iterable<? extends CompleteSet> completeSets) {
        CollectionModel<CompleteSetDto> completeSetDtos = RepresentationModelAssembler.super.toCollectionModel(completeSets);
        Link selfLink = linkTo(methodOn(CompleteSetController.class).getAllCompleteSets()).withSelfRel();
        completeSetDtos.add(selfLink);
        return completeSetDtos;
    }
}

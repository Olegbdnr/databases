package bodnar.dto.assembler;

import bodnar.domain.PatrolBot;
import bodnar.dto.PatrolBotDto;
import bodnar.controller.CompleteSetController;
import bodnar.controller.PatrolBotController;
import bodnar.controller.TeritoryController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PatrolBotDtoAssembler implements RepresentationModelAssembler<PatrolBot, PatrolBotDto> {

    @Override
    public PatrolBotDto toModel(PatrolBot patrolBot) {
        PatrolBotDto patrolBotDto = PatrolBotDto.builder()
                .id(patrolBot.getId())
                .build();
        Link selfLink = linkTo(methodOn(PatrolBotController.class).getPatrolBot(patrolBotDto.getId())).withSelfRel();
        Link completeSetLink = linkTo(methodOn(CompleteSetController.class)
                .getCompleteSet(patrolBot.getCompleteSet().getId())).withRel("completeSet");
        Link teritoryLink = linkTo(methodOn(TeritoryController.class)
                .getTeritory(patrolBot.getTeritory().getId())).withRel("teritory");
        patrolBotDto.add(selfLink);
        patrolBotDto.add(completeSetLink);
        patrolBotDto.add(teritoryLink);
        return patrolBotDto;
    }

    @Override
    public CollectionModel<PatrolBotDto> toCollectionModel(Iterable<? extends PatrolBot> patrolBots) {
        CollectionModel<PatrolBotDto> patrolBotDtos = RepresentationModelAssembler.super.toCollectionModel(patrolBots);
        Link selfLink = linkTo(methodOn(PatrolBotController.class).getAllPatrolBots()).withSelfRel();
        patrolBotDtos.add(selfLink);
        return patrolBotDtos;
    }
}

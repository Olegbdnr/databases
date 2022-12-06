package bodnar.controller;

import bodnar.domain.PatrolBot;
import bodnar.dto.PatrolBotDto;
import bodnar.dto.assembler.PatrolBotDtoAssembler;
import bodnar.service.PatrolBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patrolBots")
public class PatrolBotController {

    @Autowired
    PatrolBotService patrolBotService;

    @Autowired
    PatrolBotDtoAssembler patrolBotDtoAssembler;

    @GetMapping(path = "/{patrolBotId}")
    public ResponseEntity<PatrolBotDto> getPatrolBot(@PathVariable Integer patrolBotId) {
        PatrolBot patrolBot = patrolBotService.findById(patrolBotId);
        PatrolBotDto patrolBotDto = patrolBotDtoAssembler.toModel(patrolBot);
        return new ResponseEntity<>(patrolBotDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<PatrolBotDto>> getAllPatrolBots() {
        List<PatrolBot> patrolBots = patrolBotService.findAll();
        CollectionModel<PatrolBotDto> patrolBotDtos = patrolBotDtoAssembler.toCollectionModel(patrolBots);
        return new ResponseEntity<>(patrolBotDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{completeSetId}/{teritoryId}")
    public ResponseEntity<PatrolBotDto> addPatrolBot(@RequestBody PatrolBot patrolBot,
                                                     @PathVariable Integer completeSetId,
                                                     @PathVariable Integer teritoryId) {
        PatrolBot newPatrolBot = patrolBotService.create(patrolBot, teritoryId, completeSetId);
        PatrolBotDto patrolBotDto = patrolBotDtoAssembler.toModel(newPatrolBot);
        return new ResponseEntity<>(patrolBotDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{patrolBotId}/{completeSetId}/{teritoryId}")
    public ResponseEntity<?> updatePatrolBot(@RequestBody PatrolBot patrolBot,
                                             @PathVariable Integer patrolBotId,
                                             @PathVariable Integer completeSetId,
                                             @PathVariable Integer teritoryId) {
        patrolBotService.update(patrolBotId, patrolBot, teritoryId, completeSetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "{patrolBotId}")
    public ResponseEntity<?> deletePatrolBot(@PathVariable Integer patrolBotId) {
        patrolBotService.delete(patrolBotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

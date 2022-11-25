package bodnar.controller;

import bodnar.domain.Camera;
import bodnar.domain.CompleteSet;
import bodnar.domain.PatrolBot;
import bodnar.dto.CameraDto;
import bodnar.dto.CompleteSetDto;
import bodnar.dto.PatrolBotDto;
import bodnar.dto.assembler.CompleteSetDtoAssembler;
import bodnar.dto.assembler.PatrolBotDtoAssembler;
import bodnar.dto.assembler.CameraDtoAssembler;
import bodnar.service.CompleteSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/completeSets")
public class CompleteSetController {

    @Autowired
    CompleteSetService completeSetService;

    @Autowired
    CompleteSetDtoAssembler completeSetDtoAssembler;

    @Autowired
    CameraDtoAssembler cameraDtoAssembler;

    @Autowired
    PatrolBotDtoAssembler patrolBotDtoAssembler;

    @GetMapping(path = "/{completeSetId}")
    public ResponseEntity<CompleteSetDto> getCompleteSet(@PathVariable Integer completeSetId) {
        CompleteSet completeSet = completeSetService.findById(completeSetId);
        CompleteSetDto completeSetDto = completeSetDtoAssembler.toModel(completeSet);
        return new ResponseEntity<>(completeSetDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<CompleteSetDto>> getAllCompleteSets() {
        List<CompleteSet> completeSets = completeSetService.findAll();
        CollectionModel<CompleteSetDto> completeSetDtos = completeSetDtoAssembler.toCollectionModel(completeSets);
        return new ResponseEntity<>(completeSetDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{completeSetId}/cameras")
    public ResponseEntity<CollectionModel<CameraDto>> getCamerasForCompleteSet(@PathVariable Integer completeSetId) {
        CompleteSet completeSet = completeSetService.findById(completeSetId);
        List<Camera> camerasForCompleteSet = completeSet.getCameras();
        CollectionModel<CameraDto> cameraDtos = cameraDtoAssembler.toCollectionModel(camerasForCompleteSet);
        return new ResponseEntity<>(cameraDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{completeSetId}/patrolBots")
    public ResponseEntity<CollectionModel<PatrolBotDto>> getPatrolBotsForCompleteSet(@PathVariable Integer completeSetId) {
        CompleteSet completeSet = completeSetService.findById(completeSetId);
        List<PatrolBot> patrolBotsForCompleteSet = completeSet.getPatrolBots();
        CollectionModel<PatrolBotDto> patrolBotDtos = patrolBotDtoAssembler.toCollectionModel(patrolBotsForCompleteSet);
        return new ResponseEntity<>(patrolBotDtos, HttpStatus.OK);

    }

    @PostMapping(path = "/{manufacturerId}")
    public ResponseEntity<CompleteSetDto> addCompleteSet(@RequestBody CompleteSet completeSet, @PathVariable Integer manufacturerId) {
        CompleteSet newCompleteSet = completeSetService.create(completeSet, manufacturerId);
        CompleteSetDto completeSetDto = completeSetDtoAssembler.toModel(newCompleteSet);
        return new ResponseEntity<>(completeSetDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{completeSetId}/{manufacturerId}")
    public ResponseEntity<?> updateCompleteSet(@PathVariable Integer completeSetId,
                                               @RequestBody CompleteSet completeSet,
                                               @PathVariable Integer manufacturerId) {
        completeSetService.update(completeSetId, completeSet, manufacturerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{completeSetId}")
    public ResponseEntity<?> deleteCompleteSet(@PathVariable Integer completeSetId) {
        completeSetService.delete(completeSetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
